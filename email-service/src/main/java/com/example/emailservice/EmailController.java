package com.example.emailservice;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Created by mtumilowicz on 2018-07-28.
 */
@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("emails")
public class EmailController {
    EmailService emailService;

    @GetMapping("send/async/{message}")
    public ResponseEntity<List<String>> asyncSend(@PathVariable("message") String message) {
        List<String> ids = Arrays.asList(
                "mtumilowicz",
                "hank",
                "fjodor",
                "ernie", 
                "non-exsiting-user");
        
        List<CompletableFuture<String>> completableFutures = ids
                .stream()
                .map(id -> emailService.asyncSend(id, message))
                .collect(Collectors.toList());

        CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[]{})).join();
        
        return ResponseEntity.ok(completableFutures
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList()));
    }

    @GetMapping("send/{message}")
    public ResponseEntity<List<String>> send(@PathVariable("message") String message) {
        List<String> ids = Arrays.asList(
                "mtumilowicz",
                "hank",
                "fjodor",
                "ernie");
        
        return ResponseEntity.ok(ids
                .stream()
                .map(id -> emailService.send(id, message))
                .collect(Collectors.toList()));
    }

    @GetMapping("health")
    public void health() {

    }
}
