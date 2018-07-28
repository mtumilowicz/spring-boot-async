package com.example.emailservice;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

import static java.util.Objects.nonNull;

/**
 * Created by mtumilowicz on 2018-07-28.
 */
@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class EmailService {
    EmailSender sender;
    UserService userService;

    @Async
    public CompletableFuture<String> asyncSend(String login, String message) {
        log.info("Sending " + message + " to: " + login);
        return CompletableFuture.supplyAsync(() -> sender.send(userService.getUserById(login), message))
                .handle((s, t)-> nonNull(s) ? 
                        s : 
                        "FAIL: Sending message " + message + " to " + login + " reason:" + t.getLocalizedMessage());
    }
    
    String send(String login, String message) {
        log.info("Sending " + message + " to: " + login);
        return sender.send(userService.getUserById(login), message);
    }
}
