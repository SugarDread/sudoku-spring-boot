package com.sugardread.sudokuspringboot.run;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class RunJsonLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(RunJsonLoader.class);

    private final JdbcClientRunRepository runRepository;
    private final ObjectMapper objectMapper;

    public RunJsonLoader(JdbcClientRunRepository runRepository, ObjectMapper objectMapper) {
        this.runRepository = runRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception{
        if (runRepository.count() == 0) {
            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/runs.json")) {
                Runs runs = objectMapper.readValue(inputStream, Runs.class);
                log.info("Reading {} runs from json file", runs.runs().size());
                runRepository.saveAll(runs.runs());
            } catch (IOException e) {
                throw new RuntimeException("Failed to load json", e);
            }
        }
        else {
            log.info("Run repository already has runs");
        }
    }
}
