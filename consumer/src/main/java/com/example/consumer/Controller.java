package com.example.consumer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by mtumilowicz on 2018-07-26.
 */
@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Controller {
    
    RestTemplate restTemplate;
    ConsumerService service;
    
    @GetMapping("timeconsuming")
    public Integer timeConsuming() throws ExecutionException, InterruptedException {
//        Integer first = numbers.get(0);
//        Integer second = numbers.get(1);
//        Integer third = numbers.get(2);
//        Integer fourth = numbers.get(3);

        long start = System.currentTimeMillis();
        
        Integer first = 3;
        Integer second = 2;
        Integer third = 4;
        Integer fourth = 5;

        CompletableFuture<Integer> sum1 = service.add(first, second);
        CompletableFuture<Integer> sum2 = service.add(third, fourth);

        CompletableFuture[] array = new CompletableFuture[]{sum1, sum2};

        CompletableFuture.allOf(array);

        Integer sum1_h = sum1.get();
        Integer sum2_h = sum2.get();

        Integer forObject = restTemplate.getForObject("http://localhost:8090/sum/" + sum1_h + "/" + sum2_h, Integer.class);

        System.out.println(System.currentTimeMillis() - start);
        
        return forObject;
    }

    @GetMapping("timeconsuming2")
    public Integer timeConsuming2() {
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

        Integer forObject = restTemplate.getForObject("http://localhost:8090/sum/" + sum1 + "/" + sum2, Integer.class);

        System.out.println(System.currentTimeMillis() - start);

        return forObject;
    }
}
