package com.example.slowuserservice.rest.controller.user

import com.example.slowuserservice.domain.user.service.UserService
import spock.lang.Specification

/**
 * Created by mtumilowicz on 2018-07-28.
 */
class UserControllerTest extends Specification {
    def "test getById"() {
        given:
        def userService = Mock(UserService)

        and:
        def controller = new UserController(userService)
        
        when:
        controller.getById("login")

        then:
        1 * userService.getById("login")
    }
}
