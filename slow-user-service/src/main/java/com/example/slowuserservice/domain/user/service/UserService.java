package com.example.slowuserservice.domain.user.service;

import com.example.slowuserservice.infrastructure.exception.UserNotFoundException;
import com.example.slowuserservice.infrastructure.repository.UserRepository;
import com.example.slowuserservice.domain.user.model.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

/**
 * Created by mtumilowicz on 2018-07-28.
 */
@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    
    UserRepository repository;
    
    public User getById(String id) {
        return repository.getById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
}
