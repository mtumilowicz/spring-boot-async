package com.example.consumer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

/**
 * Created by mtumilowicz on 2018-07-27.
 */
@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConsumerService {

    RestTemplate restTemplate;

    @Async
    public CompletableFuture<Integer> add(Integer x, Integer y) {
        String url = String.format("http://localhost:8090/sum/%s/%s", x, y);
        Integer results = restTemplate.getForObject(url, Integer.class);
        return CompletableFuture.completedFuture(results);
    }
    
}
