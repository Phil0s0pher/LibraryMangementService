package com.epam.libraryservice.exception;


public class LimitExceededException extends Exception{
    public LimitExceededException(String message) {
        super(message);
    }
}
