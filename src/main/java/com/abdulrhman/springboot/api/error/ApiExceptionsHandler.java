package com.abdulrhman.springboot.api.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.lang.reflect.Field;
import java.util.List;

@ControllerAdvice
public class ApiExceptionsHandler  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiBaseExceptions.class)
    public ResponseEntity<ErrorDetails> handleApiExceptions(ApiBaseExceptions ex, WebRequest request){
   ErrorDetails Details = new ErrorDetails(ex.getMessage(),request.getDescription(false));
   return new ResponseEntity<>(Details,ex.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
       ValidationError validationError = new ValidationError();
       validationError.setUri(request.getDescription(false));

       List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
       for(FieldError f: fieldErrors){
           validationError.AddError(f.getDefaultMessage());
       }
        return new ResponseEntity<>(validationError,HttpStatus.BAD_REQUEST);
    }
}
