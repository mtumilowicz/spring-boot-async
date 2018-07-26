package com.example.slowcalculator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by mtumilowicz on 2018-07-26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerIntegrationTest {

    @Autowired
    TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void sum() {
        assertThat(restTemplate.getForObject(createURLWithPort("sum/1/2"), Integer.class), is(3));
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}