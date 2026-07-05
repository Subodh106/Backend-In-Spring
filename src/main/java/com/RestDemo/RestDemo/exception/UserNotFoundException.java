package com.RestDemo.RestDemo.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message, Long id) {
        super(message);
    }
}
