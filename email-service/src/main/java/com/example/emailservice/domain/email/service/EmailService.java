package com.example.emailservice.domain.email.service;

import com.example.emailservice.domain.user.service.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
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
    public CompletableFuture<String> asyncSend(@NonNull String login, @NonNull String message) {
        log.info("Sending " + message + " to: " + login);
        return CompletableFuture.supplyAsync(() -> sender.send(userService.getUserById(login), message))
                .handle((ok, ex) -> nonNull(ok) ?
                        ok :
                        String.format("FAIL: Sending email {%s} to %s reason: %s", 
                                message, 
                                login,
                                ex.getLocalizedMessage()));
    }

    public String send(@NonNull String login, @NonNull String message) {
        log.info("Sending " + message + " to: " + login);
        try {
            return sender.send(userService.getUserById(login), message);
        } catch (Exception t) {
            return String.format("FAIL: Sending email {%s} to %s reason: %s",
                    message,
                    login,
                    t.getLocalizedMessage());
        }
    }
}
