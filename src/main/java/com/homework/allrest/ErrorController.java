package com.homework.allrest;

import java.net.SocketTimeoutException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.homework.allrest.thing.ThingNotFoundException;
import com.homework.allrest.todo.ToDoNotFoundException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ErrorController {
    @ExceptionHandler({ ToDoNotFoundException.class,
                        ThingNotFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    private String thingNotFoundHandler(Exception e) {
        log.error(e.getMessage());
        return e.getMessage();
    }

    @ExceptionHandler(SocketTimeoutException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ResponseBody
    private String unavailableAPI(Exception e) {
        log.error(e.getMessage());
        return e.getMessage();
    }
}
