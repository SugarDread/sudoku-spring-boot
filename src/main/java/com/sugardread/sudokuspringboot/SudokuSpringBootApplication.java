package com.sugardread.sudokuspringboot;

import com.sugardread.sudokuspringboot.run.Location;
import com.sugardread.sudokuspringboot.run.Run;
import com.sugardread.sudokuspringboot.user.User;
import com.sugardread.sudokuspringboot.user.UserRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@SpringBootApplication
public class SudokuSpringBootApplication {

    private static final Logger log = LoggerFactory.getLogger(SudokuSpringBootApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(SudokuSpringBootApplication.class, args);
    }


    @Bean
    CommandLineRunner runner(UserRestClient client) {
        return args -> {
            List<User> users = client.findAll();
            log.info(users.toString());

            User user = client.findById(1);
            log.info(user.toString());

        };
    }



}
