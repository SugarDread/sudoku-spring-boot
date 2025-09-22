package com.sugardread.sudokuspringboot.run;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record Run(
        Integer id,
        @NotEmpty
        String title,
        LocalDateTime started,
        LocalDateTime completed,
        @Positive
        Integer miles,
        Location location
) {

    public Run {
        if (started.isAfter(completed)) {
            throw new IllegalArgumentException("Completed before Started");
        }
    }

}
