package com.smartcampus.exception;

// thrown when sensor is not active (e.g. maintenance)
public class SensorUnavailableException extends RuntimeException {

    public SensorUnavailableException(String message) {
        super(message);
    }
}