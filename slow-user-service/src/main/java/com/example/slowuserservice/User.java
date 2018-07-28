package com.example.slowuserservice;

import lombok.Builder;
import lombok.Value;

/**
 * Created by mtumilowicz on 2018-07-28.
 */
@Value
@Builder
class User {
    int id;
    String login;
    String firstName;
    String lastName;
    String email;
}
