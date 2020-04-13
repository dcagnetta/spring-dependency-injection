package com.example.springdependencyinjection.command;

import com.example.springdependencyinjection.command.handler.CommandHandler;

/**
 * Returns the required handler based on the command
 */
public interface HandlersProvider {
    CommandHandler<Object, Object> getHandler(Object command);
}
