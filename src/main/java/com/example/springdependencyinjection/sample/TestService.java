package com.example.springdependencyinjection.sample;

import org.springframework.stereotype.Component;

/**
 * Just to test dependency injection works on handlers
 * */
@Component
public class TestService {
    public String saySomething() {
        return "something was done.";
    }
}

