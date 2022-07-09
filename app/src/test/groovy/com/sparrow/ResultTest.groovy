package com.sparrow

import spock.lang.Specification

import java.util.function.Consumer

class ResultTest extends Specification {

    def "should create result of success"() {
        given: "any success"
        String success = "any success"

        when:
        def result = Result.success(success)

        then:
        result.success().get() == success
        !result.failure().isPresent()
    }

    def "should create result of failure"() {
        given: "any failure"
        String failure = "any failure"

        when:
        def result = Result.failure(failure)

        then:
        result.failure().get() == failure
        !result.success().isPresent()
    }

    def "should determine if success"() {
        given: "any success"
        String success = "any success"

        when:
        def result = Result.success(success)

        then:
        result.isSuccess()
    }

    def "should execute consumer if success"() {
        given:
        def anySuccess = MutableValue.of("any success")
        def result = Result.success(anySuccess)

        when:
        result.ifSuccess(new Consumer<MutableValue>() {
            @Override
            void accept(MutableValue success) {
                success.change("success indeed")
            }
        })

        then:
        anySuccess.get() == "success indeed"
    }

    private static class MutableValue {
        private String value

        private MutableValue(String value) {
            this.value = value
        }

        private static MutableValue of(String value) {
            return new MutableValue(value)
        }

        private MutableValue change(String newValue) {
            value = newValue
            return this
        }

        private String get() {
            return value
        }
    }
}