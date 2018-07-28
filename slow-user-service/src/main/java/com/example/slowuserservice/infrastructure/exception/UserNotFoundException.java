package com.example.slowuserservice.infrastructure.exception;

/**
 * Created by mtumilowicz on 2018-07-28.
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String id) {
        super("User not found - id = " + id);
    }
}
