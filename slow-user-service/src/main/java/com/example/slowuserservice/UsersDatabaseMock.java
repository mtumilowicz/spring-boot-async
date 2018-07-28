package com.example.slowuserservice;

import com.google.common.collect.ImmutableMap;

/**
 * Created by mtumilowicz on 2018-07-28.
 */
class UsersDatabaseMock {
    static final ImmutableMap<Integer, User> users = ImmutableMap.of(
            1,
            User.builder()
                    .id(1)
                    .firstName("Michal")
                    .lastName("Tumilowicz")
                    .login("mtumilowicz")
                    .email("mtumilowicz@gmail.com")
                    .build(),
            2,
            User.builder()
                    .id(2)
                    .firstName("Charles")
                    .lastName("Bukowski")
                    .login("hank")
                    .email("hank@gmail.com")
                    .build(),
            3,
            User.builder()
                    .id(3)
                    .firstName("Fyodor")
                    .lastName("Dostoevsky")
                    .login("fjodor")
                    .email("fjodor@gmail.com")
                    .build(),
            4,
            User.builder()
                    .id(4)
                    .firstName("Ernest")
                    .lastName("Hemingway")
                    .login("ernie")
                    .email("ErnestHemingway@gmail.com")
                    .build()
    );
}
