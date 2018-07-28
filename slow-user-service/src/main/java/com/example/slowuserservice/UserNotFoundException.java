package com.example.slowuserservice;

/**
 * Created by mtumilowicz on 2018-07-28.
 */
class UserNotFoundException extends RuntimeException {
    UserNotFoundException(Integer id) {
        super("User not found - id = " + id);
    }
}
