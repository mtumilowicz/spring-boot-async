package com.example.consumer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by mtumilowicz on 2018-07-28.
 */
@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Service2 {

    RestTemplate restTemplate;
    ConsumerService service;
    
    @Async
    public CompletableFuture<Long> timeConsuming() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        Integer first = 3;
        Integer second = 2;
        Integer third = 4;
        Integer fourth = 5;

        CompletableFuture<Integer> sum1 = service.add(first, second);
        CompletableFuture<Integer> sum2 = service.add(third, fourth);

        sum1.thenCombine(sum2, service::add);

//        CompletableFuture[] array = new CompletableFuture[]{sum1, sum2};

//        CompletableFuture.allOf(array);

        Integer sum1_h = sum1.get();
        Integer sum2_h = sum2.get();

        restTemplate.getForObject("http://localhost:8090/sum/" + sum1_h + "/" + sum2_h, Integer.class);

        return CompletableFuture.completedFuture(System.currentTimeMillis() - start);
    }

    @Async
    public CompletableFuture<Long> timeConsuming2() {
//        Integer first = numbers.get(0);
//        Integer second = numbers.get(1);
//        Integer third = numbers.get(2);
//        Integer fourth = numbers.get(3);

        long start = System.currentTimeMillis();

        Integer first = 3;
        Integer second = 2;
        Integer third = 4;
        Integer fourth = 5;

        Integer sum1 = restTemplate.getForObject("http://localhost:8090/sum/"+first+"/"+second, Integer.class);
        Integer sum2 = restTemplate.getForObject("http://localhost:8090/sum/"+third+"/"+fourth, Integer.class);

        restTemplate.getForObject("http://localhost:8090/sum/" + sum1 + "/" + sum2, Integer.class);

        return CompletableFuture.completedFuture(System.currentTimeMillis() - start);
    }
}
