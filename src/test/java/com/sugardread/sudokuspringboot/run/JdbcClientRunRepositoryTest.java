package com.sugardread.sudokuspringboot.run;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import(JdbcClientRunRepository.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class JdbcClientRunRepositoryTest {

    @Autowired
    JdbcClientRunRepository jdbcClientRunRepository;

    @BeforeEach
    void setUp() {
        jdbcClientRunRepository.create(new Run(1,
                "First", LocalDateTime.now(), LocalDateTime.now().plusHours(1),
                12, Location.OUTDOORS, 1));
        jdbcClientRunRepository.create(new Run(2,
                "Second", LocalDateTime.now(), LocalDateTime.now().plusHours(1),
                12, Location.OUTDOORS, 1));
    }

    @Test
    void shouldFindAll() {
        assertThat(jdbcClientRunRepository.findAll().size()).isEqualTo(2);
    }

    @Test
    void shouldFindById() {
        assertThat(jdbcClientRunRepository.findById(1).get().title()).isEqualTo("First");
    }

    @Test
    void shouldCreate() {
        jdbcClientRunRepository.create(new Run(3,
                "Third", LocalDateTime.now(), LocalDateTime.now().plusHours(1),
                12, Location.OUTDOORS, 1));
        assertThat(jdbcClientRunRepository.findAll().size()).isEqualTo(3);
    }

    @Test
    void shouldUpdate() {
        Run run = new Run(1,
                "Third", LocalDateTime.now(), LocalDateTime.now().plusHours(1),
                12, Location.OUTDOORS, 1);
        jdbcClientRunRepository.update(run, 1);
        assertThat(jdbcClientRunRepository.findById(1).get()).isEqualTo(run);
    }

    @Test
    void shouldDelete() {
        jdbcClientRunRepository.delete(1);
        assertThat(jdbcClientRunRepository.findAll().size()).isEqualTo(1);
    }
}