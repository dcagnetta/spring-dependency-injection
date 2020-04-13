package com.example.springdependencyinjection.command.impl;

import com.example.springdependencyinjection.annotations.Command;
import com.example.springdependencyinjection.command.Dispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CommandDispatcher implements Dispatcher {
	
	@Autowired
	private RunEnvironment runEnvironment;

	@Override
	public Object dispatch(Object command){
		if (isAsynchronous(command)){
			//TODO add to the queue. Queue should send this command to the RunEnvironment
			return null;
		}

		return runEnvironment.run(command);
	}

	/**
	 * @param command
	 * @return
	 */
	private boolean isAsynchronous(Object command) {
		if (! command.getClass().isAnnotationPresent(Command.class)) return false;

		Command commandAnnotation = command.getClass().getAnnotation(Command.class);		
		return commandAnnotation.asynchronous();		
	}

	
}
