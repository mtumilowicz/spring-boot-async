package com.example.slowcalculator;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by mtumilowicz on 2018-07-26.
 */
@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Controller {
    
    ComputationsService service;
    
    @GetMapping("sum/{x}/{y}")
    public ResponseEntity<Integer> sum(@PathVariable("x") int x, @PathVariable("y") int y) {
        try {
            return ResponseEntity.ok(service.add(x, y));
        } catch (InterruptedException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("health")
    public void health() {
        
    }
}
