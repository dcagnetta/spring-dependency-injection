package com.example.springdependencyinjection.command.impl;

import com.example.springdependencyinjection.command.HandlersProvider;
import com.example.springdependencyinjection.command.handler.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Dillan
 */
@Component
public class RunEnvironment {

    private HandlersProvider handlersProvider;

    @Autowired
    public RunEnvironment(HandlersProvider handlersProvider) {
        this.handlersProvider = handlersProvider;
    }

    public Object run(Object command) {
        CommandHandler<Object, Object> handler = handlersProvider.getHandler(command);

        //You can add Your own capabilities here: dependency injection, security, transaction management, logging, profiling, spying, storing commands, etc

        Object result = handler.handle(command);

        //You can add Your own capabilities here

        return result;
    }

}
