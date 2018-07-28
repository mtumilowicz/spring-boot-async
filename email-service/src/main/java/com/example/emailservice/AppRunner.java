package com.example.emailservice;

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
        // Start the clock
        long start = System.currentTimeMillis();

        ResponseEntity<List<String>> test = emailController.asyncSend("test");

        // Print results, including elapsed time
        log.info("Elapsed time: " + (System.currentTimeMillis() - start));
        log.info("--> " + test.toString());

        start = System.currentTimeMillis();

        ResponseEntity<List<String>> test2 = emailController.send("test");

        // Print results, including elapsed time
        log.info("Elapsed time: " + (System.currentTimeMillis() - start));
        log.info("--> " + test2.toString());

    }

}
