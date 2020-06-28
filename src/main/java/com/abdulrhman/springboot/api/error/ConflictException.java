package com.abdulrhman.springboot.api.error;

import org.springframework.http.HttpStatus;

public class ConflictException extends ApiBaseExceptions {

    public ConflictException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.CONFLICT;
    }
}
