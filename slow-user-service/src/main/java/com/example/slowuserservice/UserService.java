package com.example.slowuserservice;

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
    
    User getById(Integer id) {
        return repository.getById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
}
