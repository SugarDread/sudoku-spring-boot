package com.sugardread.sudokuspringboot.run;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class RunRepository {

    private static final Logger log = LoggerFactory.getLogger(RunRepository.class);
    private final JdbcClient jdbcClient;

    public RunRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Run> findAll() {
        return jdbcClient.sql("select * from run")
                .query(Run.class)
                .list();
    }

    public Optional<Run> findById(Integer id) {
        return jdbcClient.sql("select id,title,started,completed,miles,location from run where id = :id")
                .param("id", id)
                .query(Run.class)
                .optional();
    }

    public void create(Run run) {
        jdbcClient.sql("INSERT INTO RUN(id, title, started, completed, miles, location) VALUES (?,?,?,?,?,?)")
                .params(List.of(run.id(), run.title(), run.started(), run.completed(), run.miles(), run.location().toString()))
                .update();
    }


    void update(Run run, Integer id) {
        jdbcClient.sql("update run set title = ?, started = ?, completed = ?, miles = ?, location = ? where id = ?")
                .params(List.of(run.title(), run.started(), run.completed(), run.miles(), run.location().toString(), id))
                .update();
    }

    void delete(Integer id) {
        jdbcClient.sql("delete run where id = :id")
                .param("id", id)
                .update();
    }

    Integer count() {
        return jdbcClient.sql("select * from run")
                .query()
                .listOfRows()
                .size();
    }

    void saveAll(List<Run> runs) {
        runs.forEach(this::create);
    }


}
