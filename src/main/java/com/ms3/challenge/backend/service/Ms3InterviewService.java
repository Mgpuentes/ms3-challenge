package com.ms3.challenge.backend.service;

import com.ms3.challenge.backend.persistance.domain.Ms3Interview;
import com.ms3.challenge.backend.persistance.repositories.Ms3InterviewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Matthew Puentes on 12/5/19
 */

@Service
@Transactional(readOnly = true)
public class Ms3InterviewService {
    
    private static final Logger LOG = LoggerFactory.getLogger(Ms3InterviewService.class);

    private final Ms3InterviewRepository ms3InterviewRepository;

    public Ms3InterviewService(Ms3InterviewRepository ms3InterviewRepository) {
        this.ms3InterviewRepository = ms3InterviewRepository;
    }

    /**
     * Create Ms3Interview with Ms3Interview instance given.
     *
     * @param ms3Interview the Ms3Interview to save.
     * @return persisted Ms3Interview
     */
    @Transactional
    public Ms3Interview createMs3Interview(Ms3Interview ms3Interview) {
        return ms3InterviewRepository.save(ms3Interview);
    }
}
