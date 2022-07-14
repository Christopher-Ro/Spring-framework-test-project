package com.homework.allrest.thing;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.experimental.StandardException;

@StandardException
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ThingNotFoundException extends RuntimeException {
    public ThingNotFoundException(Integer id) {
        super(String.format("Thing by id %d not found.", id));
    }
}
