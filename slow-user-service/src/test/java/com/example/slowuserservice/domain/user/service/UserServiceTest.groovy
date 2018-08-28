package com.example.slowuserservice.domain.user.service

import com.example.slowuserservice.domain.user.model.User
import com.example.slowuserservice.infrastructure.exception.UserNotFoundException
import com.example.slowuserservice.infrastructure.repository.UserRepository
import spock.lang.Specification

/**
 * Created by mtumilowicz on 2018-07-28.
 */
class UserServiceTest extends Specification {
    def "test getById - found"() {
        given:
        def user = User.builder().build()

        and:
        def userRepository = Mock(UserRepository) {
            getById("login") >> Optional.of(user)
        }

        and:
        def service = new UserService(userRepository)

        expect:
        service.getById("login") == user
    }

    def "test getById - not found"() {
        given:
        def userRepository = Mock(UserRepository) {
            getById("login") >> Optional.empty()
        }

        and:
        def service = new UserService(userRepository)

        when:
        service.getById("login")

        then:
        thrown(UserNotFoundException)
    }
}
