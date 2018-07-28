package com.example.emailservice;

import com.google.common.base.Preconditions;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

import static java.util.Objects.nonNull;

/**
 * Created by mtumilowicz on 2018-07-27.
 */
@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailSender {
    
    @Async
    public CompletableFuture<String> send(@NonNull User user, @NonNull String message) {
        Preconditions.checkArgument(nonNull(user.getEmail()));
        
        return CompletableFuture.completedFuture("email: " + message + " sent to: " + user.getEmail());
    }
    
}
