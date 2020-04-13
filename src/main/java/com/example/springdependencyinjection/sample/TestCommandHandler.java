package com.example.springdependencyinjection.sample;

import com.example.springdependencyinjection.annotations.CommandHandlerAnnotation;
import com.example.springdependencyinjection.command.handler.CommandHandler;

@CommandHandlerAnnotation
public class TestCommandHandler implements CommandHandler<TestCommand, Void> {

    @Override
    public Void handle(TestCommand command) {
        System.out.println(command.getName() + " called!");

        return null;
    }

}
