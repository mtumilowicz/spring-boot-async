package com.example.consumer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;

/**
 * Created by mtumilowicz on 2018-07-26.
 */
@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Controller {

    RestTemplate restTemplate;
    Service2 service;

    @GetMapping("report")
    public String compareTimes() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        String timeConsumingComparison = service.timeConsuming()
                .thenCombine(
                        service.timeConsuming2(),
                        (x, y) -> String.format("With @Async: %s ms, without @Async: %s ms", x, y)
                ).join();
        long l = System.currentTimeMillis() - start;

        return timeConsumingComparison + ", compareTimes completed in: " + l + " ms";
    }
}
