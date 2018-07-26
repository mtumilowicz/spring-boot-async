package com.example.consumer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by mtumilowicz on 2018-07-26.
 */
@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Controller {
    
    RestTemplate template;
    
    @GetMapping("timeconsuming")
    public Integer timeConsuming() {
        return template.getForObject("http://localhost:8090/sum/1/2", Integer.class);
    }
}
