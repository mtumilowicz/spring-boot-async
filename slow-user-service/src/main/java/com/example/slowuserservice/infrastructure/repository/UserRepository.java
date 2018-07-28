package com.example.slowuserservice.infrastructure.repository;

import com.example.slowuserservice.infrastructure.database.UsersDatabaseMock;
import com.example.slowuserservice.domain.user.model.User;
import com.example.slowuserservice.infrastructure.utils.ThreadUtils;
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
    
    public Optional<User> getById(String id) {
        ThreadUtils.sleep(delay);
        return Optional.ofNullable(UsersDatabaseMock.users.get(id));
    }
}
