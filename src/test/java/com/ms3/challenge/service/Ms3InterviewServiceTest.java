package com.ms3.challenge.service;

import com.ms3.challenge.backend.persistance.domain.Ms3Interview;
import com.ms3.challenge.backend.service.Ms3InterviewService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

/**
 * @author Matthew Puentes on 12/10/19
 */

@SpringBootTest
public class Ms3InterviewServiceTest {

    @Autowired
    Ms3InterviewService ms3InterviewService;

    @Test
    void read() throws IOException {
        List<Ms3Interview> returnValue = ms3InterviewService.read("ms3Interview.csv");

        Assertions.assertAll(() -> {
            Assertions.assertNotNull(returnValue);
            Assertions.assertFalse(returnValue.isEmpty());
            Assertions.assertEquals(25, returnValue.size());
        });
    }

    @Test
    void parse() {
    }

    @Test
    void insert() throws IOException {

    }

    @Test
    void save() {
        Ms3Interview ms3Interview = new Ms3Interview();
        Ms3Interview savedMs3 = ms3InterviewService.save(ms3Interview);

        Assertions.assertAll(() -> {
            Assertions.assertNotNull(savedMs3);
            Assertions.assertEquals(ms3Interview, savedMs3);

        });
    }

    @Test
    void saveAll() {
    }
}
