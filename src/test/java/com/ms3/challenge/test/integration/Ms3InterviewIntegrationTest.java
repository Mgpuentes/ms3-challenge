package com.ms3.challenge.test.integration;

import com.ms3.challenge.backend.persistance.domain.Ms3Interview;
import com.ms3.challenge.backend.persistance.repositories.Ms3InterviewRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author Matthew Puentes on 12/5/19
 */

@DataJpaTest
public class Ms3InterviewIntegrationTest {

    @Autowired
    private Ms3InterviewRepository ms3InterviewRepository;

    @Test
    public void createNewMs3Interview() throws Exception {

    }
}
