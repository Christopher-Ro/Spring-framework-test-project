package com.homework.allrest.todo;

import java.io.IOException;
import java.net.URI;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

public class ToDoResponseErrorHandler implements ResponseErrorHandler{

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode() != HttpStatus.OK;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {}

    @Override
    public void handleError(URI url, HttpMethod method,
                            ClientHttpResponse response) throws IOException {
        String path = url.getPath();
        handleError(response);
        throw new ToDoNotFoundException(Integer
                .parseInt(path.substring(path.lastIndexOf('/') + 1)));
    }
}
