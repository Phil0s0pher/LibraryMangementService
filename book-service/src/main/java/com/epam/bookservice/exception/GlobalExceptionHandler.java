package com.epam.bookservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BookNotFoundException.class)
    public Map<String , String> bookNotFoundHandler(BookNotFoundException exception){
        Map<String , String> errors = new HashMap<>();
        errors.put("error",exception.getMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateBookException.class)
    public Map<String , String> duplicateBookHandler(DuplicateBookException exception){
        Map<String , String> errors = new HashMap<>();
        errors.put("error",exception.getMessage());
        return errors;
    }
}
