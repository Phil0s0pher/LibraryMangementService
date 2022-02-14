package com.epam.bookservice.exception;

public class DuplicateBookException extends Exception{
    public DuplicateBookException(String message) {
        super(message);
    }
}
