package com.abdulrhman.springboot.api.error;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ApiBaseExceptions {
    public NotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
