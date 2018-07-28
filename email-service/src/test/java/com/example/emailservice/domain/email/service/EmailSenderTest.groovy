package com.example.emailservice.domain.email.service

import com.example.emailservice.domain.user.model.User
import spock.lang.Specification 
/**
 * Created by mtumilowicz on 2018-07-28.
 */
class EmailSenderTest extends Specification {
    def "test send - empty email"() {
        given:
        User user = User.builder().build()
        
        when:
        new EmailSender().send(user, '')
        
        then:
        thrown(IllegalArgumentException)
    }

    def "test send - empty login"() {
        given:
        User user = User.builder().email("a@a.pl").build()

        expect:
        new EmailSender().send(user, 'test') == "Email: {test} was sent to: a@a.pl."
    }

    def "test send - not empty login"() {
        given:
        User user = User.builder().login("a").email("a@a.pl").build()

        expect:
        new EmailSender().send(user, 'test') == "Email: {test} was sent to: a."
    }
}
