package com.example.emailservice;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * Created by mtumilowicz on 2018-07-28.
 */
@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailService {
    EmailSender sender;
    UserService userService;

    @Async
    public CompletableFuture<String> asyncSend(Integer id, String message) {
        return CompletableFuture.completedFuture(sender.send(userService.getUserById(id), message));
    }
    
    String send(Integer id, String message) {
        return sender.send(userService.getUserById(id), message);
    }
}
