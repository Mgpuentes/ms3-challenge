package com.ms3.challenge.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.ms3.challenge.backend.persistance.domain.Ms3Interview;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

/**
 * @author Matthew Puentes on 12/10/19
 */

@Configuration
public class ApplicationConfig {
    @Bean
    public CsvMapper mapper() {
        CsvMapper csvMapper = new CsvMapper();
        csvMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        csvMapper.enable(CsvParser.Feature.IGNORE_TRAILING_UNMAPPABLE);
        csvMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return csvMapper;
    }

    @Bean
    public Iterable<Ms3Interview> ms3Interviews() {
        return new ArrayList<>();
    }
}
