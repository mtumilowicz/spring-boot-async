package com.example.slowuserservice.rest.controller.user;

import com.example.slowuserservice.domain.user.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by mtumilowicz on 2018-07-28.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {
    @Autowired
    TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void getById_status_mtumilowicz() {
        assertThat(restTemplate
                        .getForEntity(
                                createURLWithPort("users/mtumilowicz"),
                                null)
                        .getStatusCode(),
                is(HttpStatus.OK));
    }

    @Test
    public void getById_response_mtumilowicz() {
        assertThat(restTemplate
                        .getForObject(
                                createURLWithPort("users/mtumilowicz"),
                                User.class),
                is(User.builder()
                        .firstName("Michal")
                        .lastName("Tumilowicz")
                        .login("mtumilowicz")
                        .email("mtumilowicz@gmail.com")
                        .build()));
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}