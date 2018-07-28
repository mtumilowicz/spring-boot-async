package com.example.emailservice.domain.user.service

import com.example.emailservice.domain.user.model.User
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

/**
 * Created by mtumilowicz on 2018-07-28.
 */
class UserServiceTest extends Specification {
    def "test getUserById"() {
        given:
        def user = User.builder()
                .login("login")
                .email("a@a.pl")
                .build()

        and:
        def restTemplate = Mock(RestTemplate) {
            getForObject({it.toString().contains('users/login')} as String, User.class) >> user
        }

        and:
        def service = new UserService(restTemplate)

        expect:
        service.getUserById("login") == user
    }
}
