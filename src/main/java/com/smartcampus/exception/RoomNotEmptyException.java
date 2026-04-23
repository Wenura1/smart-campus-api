package com.smartcampus.exception;

// thrown when trying to delete room with sensors
public class RoomNotEmptyException extends RuntimeException {

    public RoomNotEmptyException(String message) {
        super(message);
    }
}