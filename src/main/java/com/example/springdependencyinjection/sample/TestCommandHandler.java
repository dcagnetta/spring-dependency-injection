package com.example.springdependencyinjection.sample;

import com.example.springdependencyinjection.annotations.CommandHandlerAnnotation;
import com.example.springdependencyinjection.command.handler.CommandHandler;

@CommandHandlerAnnotation
public class TestCommandHandler implements CommandHandler<TestCommand, Void> {

    private TestService service;

    public TestCommandHandler(TestService service) {
        this.service = service;
    }

    @Override
    public Void handle(TestCommand command) {
        System.out.println(command.getName() + " called!\t" + service.saySomething());

        return null; // needed for void command handlers
    }

}
