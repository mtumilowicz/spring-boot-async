package com.example.emailservice;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

/**
 * Created by mtumilowicz on 2018-07-28.
 */
@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    
    RestTemplate restTemplate;

    @Async
    public CompletableFuture<User> getUserById(Integer id) {
        String url = String.format("http://localhost:8090/users/%s", id);
        User user = restTemplate.getForObject(url, User.class);
        return CompletableFuture.completedFuture(user);
    }
}
