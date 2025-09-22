package com.sugardread.sudokuspringboot.run;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RunNotFound extends RuntimeException {
    public RunNotFound() {
        super("Run not found");
    }
}
