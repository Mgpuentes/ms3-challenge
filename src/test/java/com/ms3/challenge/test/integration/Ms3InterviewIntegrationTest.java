package com.ms3.challenge.test.integration;

import com.ms3.challenge.backend.persistance.domain.Ms3Interview;
import com.ms3.challenge.backend.persistance.repositories.Ms3InterviewRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Matthew Puentes on 12/5/19
 */

@SpringBootTest
public class Ms3InterviewIntegrationTest {

    @Autowired
    private Ms3InterviewRepository ms3InterviewRepository;

    @Test
    public void createNewMs3Interview() throws Exception {
        Ms3Interview ms3Interview = new Ms3Interview();
        ms3Interview.setId(1);
        ms3Interview.setA("Bob");
        ms3Interview.setB("Hill");
        ms3Interview.setC("Bob@test.com");
        ms3Interview.setD("Male");
        ms3Interview.setE("data:image/png:base");
        ms3Interview.setF("visa");
        ms3Interview.setG(2.00);
        ms3Interview.setH(true);
        ms3Interview.setI(false);
        ms3Interview.setJ("Roman");

        Ms3Interview savedMs3 =  ms3InterviewRepository.save(ms3Interview);
        Assertions.assertNotNull(savedMs3);
    }
}
