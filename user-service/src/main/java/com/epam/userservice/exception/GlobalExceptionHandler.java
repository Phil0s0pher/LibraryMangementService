package com.epam.userservice.exception;

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
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String , String> userNotFoundHandler(UserNotFoundException exception){
        Map<String , String> errors = new HashMap<>();
        errors.put("error",exception.getMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateUserException.class)
    public Map<String , String> duplicateUserHandler(DuplicateUserException exception){
        Map<String , String> errors = new HashMap<>();
        errors.put("error",exception.getMessage());
        return errors;
    }

}
