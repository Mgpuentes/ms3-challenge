package com.ms3.challenge.task;

import com.ms3.challenge.backend.persistance.domain.Ms3Interview;
import com.ms3.challenge.backend.service.Ms3InterviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Matthew Puentes on 12/10/19
 */

@Slf4j
@Component
public class RecordWorker implements Runnable{
    private final Ms3InterviewService ms3InterviewService;
    private final Iterable<Ms3Interview> chunk;

    @Autowired
    public RecordWorker(Ms3InterviewService ms3InterviewService, Iterable<Ms3Interview> chunk) {
        this.ms3InterviewService = ms3InterviewService;
        this.chunk = chunk;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        LOG.debug("{} starting task now...", Thread.currentThread().getName());
        ms3InterviewService.saveAll(chunk);
    }
}
