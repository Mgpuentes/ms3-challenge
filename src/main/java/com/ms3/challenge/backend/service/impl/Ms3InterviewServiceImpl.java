package com.ms3.challenge.backend.service.impl;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.ms3.challenge.backend.persistance.domain.Ms3Interview;
import com.ms3.challenge.backend.persistance.repositories.Ms3InterviewRepository;
import com.ms3.challenge.backend.service.Ms3InterviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Matthew Puentes on 12/10/19
 */

@Service
@Slf4j
public class Ms3InterviewServiceImpl implements Ms3InterviewService {

    private final CsvMapper csvMapper;
    private final Ms3InterviewRepository ms3InterviewRepository;

    public Ms3InterviewServiceImpl(CsvMapper csvMapper, Ms3InterviewRepository ms3InterviewRepository) {
        this.csvMapper = csvMapper;
        this.ms3InterviewRepository = ms3InterviewRepository;
    }

    /**
     * Read a CSV for a given file.
     *
     * @param fileName input file
     * @return list of stuff
     * @throws IOException if any error occurs reaching out to file system
     */
    @Override
    public List<Ms3Interview> read(String fileName) throws IOException {
        // have both valid and invalid list
        List<Ms3Interview> validRecords = new ArrayList<>();
        List<Ms3Interview> invalidRecords = new ArrayList<>();

        // read from a csv file (fileName)
        extractDataFromFile(fileName, validRecords, invalidRecords);

        LOG.debug("# of records received: {}", Integer.sum(validRecords.size(), invalidRecords.size()));
        LOG.debug("# of records successful {}", validRecords.size());
        LOG.debug("# of records failed {}", invalidRecords.size());

        if (!invalidRecords.isEmpty()) {
            insert(invalidRecords, fileName);
        }

        return validRecords;
    }

    private void extractDataFromFile(String fileName, List<Ms3Interview> validRecords, List<Ms3Interview> invalidRecords) throws IOException {
        CsvSchema schema = csvMapper.schemaFor(Ms3Interview.class).withHeader().withColumnReordering(true);
        File file = new ClassPathResource(fileName).getFile();
        LOG.debug("Created a file from the csv file in resources {}", file);

        MappingIterator<Ms3Interview> iterator = csvMapper.readerFor(Ms3Interview.class).with(schema).readValues(file);

        while (iterator.hasNext()) {
            splitParsedRecord(validRecords, invalidRecords, iterator.next());
        }
    }

    private void splitParsedRecord(List<Ms3Interview> validRecords, List<Ms3Interview> invalidRecords, Ms3Interview next) {
        Ms3Interview ms3Interview;
        try {
            ms3Interview = parse(next);
            validRecords.add(ms3Interview);
        } catch (InvalidObjectException | IllegalAccessException e) {
            invalidRecords.add(next);
        }
    }

    /**
     * Validate and pass object.
     *
     * @param ms3Interview
     * @return
     * @throws IllegalArgumentException
     * @throws InvalidObjectException
     */
    @Override
    public Ms3Interview parse(Ms3Interview ms3Interview) throws IllegalArgumentException, InvalidObjectException, IllegalAccessException {
        // generate a random boolean variable from the current thread
        Field[] fields = ms3Interview.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Class<?> fieldType = field.getType();
            Object state = field.get(ms3Interview);
            if (fieldType == String.class && (Objects.isNull(state) || StringUtils.isEmpty(state))) {
                LOG.error("Bad record encountered with a missing field {} and record {}", field.getName(), ms3Interview);

                throw new InvalidObjectException("Record is not valid");
            }
        }
        return ms3Interview;
    }

    /**
     * Writes to file.
     *
     * @param ms3Interviews
     * @return
     * @throws IOException
     */
    @Override
    @Transactional
    public boolean insert(List<Ms3Interview> ms3Interviews, String fileName) throws IOException {
        String[] splittedName = fileName.split("\\.");
        String outputFileName = splittedName[0].concat("-bad.").concat(splittedName[1]);

        CsvSchema schema = csvMapper.schemaFor(Ms3Interview.class).withHeader().withColumnReordering(true);
        File tempFile = getFile(outputFileName);
        SequenceWriter sequenceWriter = csvMapper.writerFor(Ms3Interview.class).with(schema).writeValues(tempFile);
        sequenceWriter.writeAll(ms3Interviews);

        return false;
    }

    @Override
    public Ms3Interview save(Ms3Interview ms3Interview) {
        return ms3InterviewRepository.save(ms3Interview);
    }

    @Override
    @Transactional
    public List<Ms3Interview> saveAll(Iterable<Ms3Interview> ms3Interviews) {
        return ms3InterviewRepository.saveAll(ms3Interviews);
    }

    private File getFile(String outputFileName) throws IOException {
        File file = new File("output" + File.separator + outputFileName);
        if (file.createNewFile()) {
            LOG.debug("File is newly created as {}", file);
        } else {
            LOG.debug("File already exists as {}", file);
        }
        return file;
    }
}
