package com.ms3.challenge.bootstrap;

import com.ms3.challenge.backend.persistance.domain.Ms3Interview;
import com.ms3.challenge.backend.service.Ms3InterviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author Matthew Puentes on 12/10/19
 */

@Component
@Slf4j
public class Ms3InterviewDriver implements CommandLineRunner {
    private final Ms3InterviewService ms3InterviewService;

    public Ms3InterviewDriver(Ms3InterviewService ms3InterviewService) {
        this.ms3InterviewService = ms3InterviewService;
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.debug("Begin Reading from CSV file...");

        List<Ms3Interview> returnedValue = ms3InterviewService.read("ms3Interview.csv");

        LOG.debug("Saving successful data to database...");

        int chunkSize = returnedValue.size() / 4;

        AtomicInteger counter = new AtomicInteger();
        Collection<List<Ms3Interview>> chunksOfRecords = returnedValue.parallelStream()
                .collect(Collectors.groupingBy(ms3Interview -> counter.getAndIncrement() / chunkSize))
                .values();

        List<Runnable> workers = new ArrayList<>();

        // initialize workers to start work.
        chunksOfRecords.forEach(chunksOfRecord -> workers.add(new RecordWorker(ms3InterviewService, chunksOfRecord)));

        for (Runnable worker : workers) {
            worker.run();
        }
    }
}
