package com.example.slowuserservice.domain.user.model;

import lombok.Builder;
import lombok.Value;

/**
 * Created by mtumilowicz on 2018-07-28.
 */
@Value
@Builder
public
class User {
    String login;
    String firstName;
    String lastName;
    String email;
}
