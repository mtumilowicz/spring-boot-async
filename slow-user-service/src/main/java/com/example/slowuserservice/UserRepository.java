package com.example.slowuserservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by mtumilowicz on 2018-07-28.
 */
@Repository
public class UserRepository {

    @Value("${user.repository.delay.seconds}")
    private Integer delay;
    
    Optional<User> getById(Integer id) {
        ThreadUtils.sleep(delay);
        return Optional.ofNullable(UsersDatabaseMock.users.get(id));
    }
}
