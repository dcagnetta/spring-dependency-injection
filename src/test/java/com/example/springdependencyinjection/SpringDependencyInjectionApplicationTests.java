package com.example.springdependencyinjection;

import com.example.springdependencyinjection.command.Dispatcher;
import com.example.springdependencyinjection.sample.TestCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringDependencyInjectionApplicationTests {

	@Autowired
	Dispatcher dispatcher;

	@Test
	void contextLoads() {
	}

	@Test
	public void should_send_test_command(){
		var cmd = new TestCommand("testing the command");
		dispatcher.dispatch(cmd);
	}

}
