package com.epam.libraryservice.exception;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public Map<String , String> notFoundHandler(NotFoundException exception){
        Map<String , String> map = new HashMap<>();
        map.put("error" , exception.getMessage());
        return map;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(LimitExceededException.class)
    public Map<String , String> limitExceededHandler(LimitExceededException exception){
        Map<String , String> map = new HashMap<>();
        map.put("error" , exception.getMessage());
        return map;
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Map<String , String>> feignHandler(FeignException exception){
        Map<String , String> map = new HashMap<>();
        map.put("error" , exception.getMessage());
        return new ResponseEntity<>(map , HttpStatus.valueOf(exception.status()));
    }
}

