package com.example.slowuserservice;

import com.google.common.collect.ImmutableMap;

/**
 * Created by mtumilowicz on 2018-07-28.
 */
class UsersDatabaseMock {
    static final ImmutableMap<String, User> users = ImmutableMap.of(
            "mtumilowicz",
            User.builder()
                    .firstName("Michal")
                    .lastName("Tumilowicz")
                    .login("mtumilowicz")
                    .email("mtumilowicz@gmail.com")
                    .build(),
            "hank",
            User.builder()
                    .firstName("Charles")
                    .lastName("Bukowski")
                    .login("hank")
                    .email("hank@gmail.com")
                    .build(),
            "fjodor",
            User.builder()
                    .firstName("Fyodor")
                    .lastName("Dostoevsky")
                    .login("fjodor")
                    .email("fjodor@gmail.com")
                    .build(),
            "ernie",
            User.builder()
                    .firstName("Ernest")
                    .lastName("Hemingway")
                    .login("ernie")
                    .email("ErnestHemingway@gmail.com")
                    .build()
    );
}
