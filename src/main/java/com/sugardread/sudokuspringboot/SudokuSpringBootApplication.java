package com.sugardread.sudokuspringboot;

import com.sugardread.sudokuspringboot.run.Location;
import com.sugardread.sudokuspringboot.run.Run;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@SpringBootApplication
public class SudokuSpringBootApplication {

    private static final Logger log = LoggerFactory.getLogger(SudokuSpringBootApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(SudokuSpringBootApplication.class, args);
    }

/*
    @Bean
    CommandLineRunner runner() {
        return args -> {
            Run run = new Run(1, "First", LocalDateTime.now(), LocalDateTime.now().plusHours(1), 10, Location.OUTDOORS);
            log.info("Run: " + run);
        };
    }
*/


}
