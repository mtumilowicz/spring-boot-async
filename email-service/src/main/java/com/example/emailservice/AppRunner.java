package com.example.emailservice;

import com.example.emailservice.rest.controller.email.EmailController;
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
        long start = System.currentTimeMillis();
        ResponseEntity<List<String>> test = emailController.asyncSend("test");
        log.info("Async Elapsed time: " + (System.currentTimeMillis() - start));
        log.info("--> " + test.toString());

        start = System.currentTimeMillis();

        ResponseEntity<List<String>> test2 = emailController.send("test");

        // Print results, including elapsed time
        log.info("Not-async Elapsed time: " + (System.currentTimeMillis() - start));
        log.info("--> " + test2.toString());

    }

}
