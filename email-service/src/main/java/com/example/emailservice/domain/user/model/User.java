package com.example.emailservice.domain.user.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;

/**
 * Created by mtumilowicz on 2018-07-28.
 */
@Value
@Builder
@JsonIgnoreProperties(ignoreUnknown=true)
public class User {
    String login;
    String email;
}
