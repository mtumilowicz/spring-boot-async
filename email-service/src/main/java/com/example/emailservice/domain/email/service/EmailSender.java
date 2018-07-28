package com.example.emailservice.domain.email.service;

import com.example.emailservice.domain.user.model.User;
import com.google.common.base.Preconditions;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

/**
 * Created by mtumilowicz on 2018-07-27.
 */
@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailSender {
    
    String send(@NonNull User user, @NonNull String message) {
        Preconditions.checkArgument(nonNull(user.getEmail()));
        
        return String.format("Email: {%s} was sent to: %s.", 
                message,
                StringUtils.defaultIfEmpty(user.getLogin(), user.getEmail()));
    }
    
}
