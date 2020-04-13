package com.example.springdependencyinjection.sample;

/**
 * This class is not annotated for Spring DI
 * */
public class TestClass<TType> {
    public void testMethod(TType t) {
        System.out.println("Test Method");
    }
}
