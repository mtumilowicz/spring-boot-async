package com.example.emailservice.domain.email.service

import com.example.emailservice.domain.user.model.User
import com.example.emailservice.domain.user.service.UserService
import spock.lang.Specification

/**
 * Created by mtumilowicz on 2018-07-28.
 */
class EmailServiceTest extends Specification {
    def "asyncSend - without exception"() {
        given:
        def emailSender = new EmailSender()
        def userService = Mock(UserService) {
            getUserById(_) >> User.builder()
                    .login(_login)
                    .email("a@a.pl")
                    .build()
        }

        and:
        def emailService = new EmailService(emailSender, userService)

        when:
        def send = emailService.asyncSend(_login, _message).join()

        then:
        send == "Email: {$_message} was sent to: $_login."

        where:
        _login  | _message
        "login" | "message"
    }

    def "asyncSend - exception"() {
        given:
        def emailSender = Mock(EmailSender) {
            send(*_) >> { throw new RuntimeException(_reason) }
        }

        def userService = Mock(UserService) {
            getUserById(_) >> User.builder()
                    .login(_login)
                    .email("a@a.pl")
                    .build()
        }

        and:
        def emailService = new EmailService(emailSender, userService)

        when:
        def send = emailService.asyncSend("login", _message).join()

        then:
        send == "FAIL: Sending email {$_message} to $_login reason: java.lang.RuntimeException: $_reason"

        where:
        _login  | _message  | _reason
        "login" | "message" | "reason"
    }

    def "send - without exception"() {
        given:
        def emailSender = new EmailSender()
        def userService = Mock(UserService) {
            getUserById(_) >> User.builder()
                    .login(_login)
                    .email("a@a.pl")
                    .build()
        }

        and:
        def emailService = new EmailService(emailSender, userService)

        when:
        def send = emailService.send(_login, _message)

        then:
        send == "Email: {$_message} was sent to: $_login."

        where:
        _login  | _message
        "login" | "message"
    }

    def "send - exception"() {
        given:
        def emailSender = Mock(EmailSender) {
            send(*_) >> { throw new RuntimeException(_reason) }
        }

        def userService = Mock(UserService) {
            getUserById(_) >> User.builder()
                    .login(_login)
                    .email("a@a.pl")
                    .build()
        }

        and:
        def emailService = new EmailService(emailSender, userService)

        when:
        def send = emailService.send("login", _message)

        then:
        send == "FAIL: Sending email {$_message} to $_login reason: $_reason"

        where:
        _login  | _message  | _reason
        "login" | "message" | "reason"
    }
}
