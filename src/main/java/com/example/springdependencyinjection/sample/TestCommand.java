package com.example.springdependencyinjection.sample;

import com.example.springdependencyinjection.annotations.Command;
import lombok.Getter;
import lombok.Setter;

@Command
@Getter
@Setter
public class TestCommand {
    private String name;

    public TestCommand(String name) {
        this.name = name;
    }
}
