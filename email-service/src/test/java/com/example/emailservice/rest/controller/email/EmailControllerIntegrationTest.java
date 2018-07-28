package com.example.emailservice.rest.controller.email;

import com.google.common.collect.ImmutableMap;
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
public class EmailControllerIntegrationTest {

    @Autowired
    TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private static ImmutableMap<String, String> request = ImmutableMap.of(
            "mtumilowicz", "4mtumilowicz",
            "hank", "4hank",
            "fjodor", "4fjodor",
            "ernie", "4ernie",
            "non-existing-user", "4non-existing-user"
    );

    @Test
    public void asyncSend_status() {
        assertThat(restTemplate
                        .postForEntity(
                                createURLWithPort("emails/send/async"),
                                request,
                                Object[].class)
                        .getStatusCode(),
                is(HttpStatus.OK));
    }

    @Test
    public void send_status() {
        assertThat(restTemplate
                        .postForEntity(
                                createURLWithPort("emails/send"),
                                request,
                                Object[].class)
                        .getStatusCode(),
                is(HttpStatus.OK));
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}