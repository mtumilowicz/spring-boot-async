package com.example.emailservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;

/**
 * Created by mtumilowicz on 2018-07-28.
 */
@Value
@Builder
@JsonIgnoreProperties(ignoreUnknown=true)
class User {
    Integer id;
    String login;
    String email;
}
