package com.homework.allrest.todo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.experimental.StandardException;

@StandardException
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ToDoNotFoundException extends RuntimeException {
    public ToDoNotFoundException(Integer id) {
        super(String.format("ToDo by id %d not found.", id));
    }
}
