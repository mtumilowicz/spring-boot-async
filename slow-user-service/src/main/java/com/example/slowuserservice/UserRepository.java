package com.example.slowuserservice;

import com.google.common.collect.ImmutableCollection;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by mtumilowicz on 2018-07-28.
 */
@Repository
public class UserRepository {
    Optional<User> getById(Integer id) {
        return Optional.ofNullable(UsersMock.users.get(id));
    }

    ImmutableCollection<User> findAll() {
        return UsersMock.users.values();
    }
}
