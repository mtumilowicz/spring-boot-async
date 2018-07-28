package com.example.emailservice;

import com.example.emailservice.rest.controller.email.EmailController;
import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mtumilowicz on 2018-07-28.
 */
@Component
@AllArgsConstructor
@Slf4j
public class AppRunner implements CommandLineRunner {

    private final EmailController emailController;

    @Override
    public void run(String... args) {
        ImmutableMap<String, String> loginMessageMap = ImmutableMap.of(
                "mtumilowicz", "4mtumilowicz",
                "hank", "4hank",
                "fjodor", "4fjodor",
                "ernie", "4ernie",
                "non-existing-user", "4non-existing-user"
        );

        long start = System.currentTimeMillis();
        ResponseEntity<List<String>> test = emailController.asyncSend(loginMessageMap);
        log.info("Async Elapsed time: " + (System.currentTimeMillis() - start));
        log.info("--> " + test.toString());

        start = System.currentTimeMillis();

        ResponseEntity<List<String>> test2 = emailController.send(loginMessageMap);

        // Print results, including elapsed time
        log.info("Not-async Elapsed time: " + (System.currentTimeMillis() - start));
        log.info("--> " + test2.toString());

    }

}
