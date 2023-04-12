package com.abnamro.recipe.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends RuntimeException implements ICustomException {

    private HttpStatus status = HttpStatus.NOT_FOUND;

    public NotFoundException(String message) {
        super(message);
    }


    public HttpStatus getStatus() {
        return status;
    }
}
