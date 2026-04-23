package com.smartcampus.exception;

// thrown when linked resource (room) not found
public class LinkedResourceNotFoundException extends RuntimeException {

    public LinkedResourceNotFoundException(String message) {
        super(message);
    }
}