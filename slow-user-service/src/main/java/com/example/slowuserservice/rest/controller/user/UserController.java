package com.example.slowuserservice.rest.controller.user;

import com.example.slowuserservice.domain.user.model.User;
import com.example.slowuserservice.domain.user.service.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mtumilowicz on 2018-07-28.
 */
@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("users")
public class UserController {

    UserService service;
    
    @GetMapping("{login}")
    public ResponseEntity<User> getById(@PathVariable("login") String login) {
        return ResponseEntity.ok(service.getById(login));
    }

    @GetMapping("health")
    public void health() {

    }
}
