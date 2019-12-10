package com.ms3.challenge.backend.service;

import com.ms3.challenge.backend.persistance.domain.Ms3Interview;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.util.List;

/**
 * @author Matthew Puentes on 12/5/19
 */

public interface Ms3InterviewService {

    /**
     * Read a CSV for a given file.
     *
     * @param fileName input file
     * @return list of stuff
     * @throws IOException if any error occurs reaching out to file system
     */
        List<Ms3Interview> read(String fileName) throws IOException;


    /**
     * Validate and pass object.
     *
     * @param ms3Interview
     * @return
     * @throws IllegalArgumentException
     * @throws InvalidObjectException
     */
    Ms3Interview parse(Ms3Interview ms3Interview) throws IllegalArgumentException, InvalidObjectException, IllegalAccessException;

    /**
     * Writes to file.
     *
     * @param ms3Interviews
     * @return
     * @throws IOException
     */
    boolean insert(List<Ms3Interview> ms3Interviews, String fileName) throws IOException;


    Ms3Interview save(Ms3Interview ms3Interview);


    List<Ms3Interview> saveAll(Iterable<Ms3Interview> ms3Interviews);
}
