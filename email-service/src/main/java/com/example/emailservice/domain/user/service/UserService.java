package com.example.emailservice.domain.user.service;

import com.example.emailservice.domain.user.model.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by mtumilowicz on 2018-07-28.
 */
@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    
    RestTemplate restTemplate;
    
    public User getUserById(@NonNull String id) {
        return restTemplate.getForObject(String.format("http://localhost:8090/users/%s", id), User.class);
    }
}
