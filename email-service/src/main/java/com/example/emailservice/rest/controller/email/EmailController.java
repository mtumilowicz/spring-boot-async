package com.example.emailservice.rest.controller.email;

import com.example.emailservice.domain.email.service.EmailService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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
    
//    example json
//    {
//            "mtumilowicz": "4mtumilowicz",
//            "hank": "4hank",
//            "fjodor": "4fjodor",
//            "ernie": "4ernie",
//            "non-existing-user": "4non-existing-user"
//    }

    @PostMapping("send/async")
    public ResponseEntity<List<String>> asyncSend(@RequestBody Map<String, String> loginMessageMap) {
        List<CompletableFuture<String>> completableFutures = loginMessageMap
                .entrySet()
                .stream()
                .map(entry -> emailService.asyncSend(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[]{})).join();

        return ResponseEntity.ok(completableFutures
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList()));
    }

    @PostMapping("send")
    public ResponseEntity<List<String>> send(@RequestBody Map<String, String> loginMessageMap) {
        return ResponseEntity.ok(loginMessageMap
                .entrySet()
                .stream()
                .map(entry -> emailService.send(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList()));
    }

    @GetMapping("health")
    public void health() {

    }
}
