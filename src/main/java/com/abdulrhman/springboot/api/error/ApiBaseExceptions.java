package com.abdulrhman.springboot.api.error;

import org.springframework.http.HttpStatus;

public  abstract class ApiBaseExceptions extends RuntimeException {
    public ApiBaseExceptions(String message) {
        super(message);
    }
    public abstract HttpStatus getStatus();
}
