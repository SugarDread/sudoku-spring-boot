package com.sugardread.sudokuspringboot.run;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public record Run(
        @Id
        Integer id,
        @NotEmpty
        String title,
        LocalDateTime started,
        LocalDateTime completed,
        @Positive
        Integer miles,
        Location location,
        Integer version
) {

    public Run {
        if (started.isAfter(completed)) {
            throw new IllegalArgumentException("Completed before Started");
        }
    }

}
