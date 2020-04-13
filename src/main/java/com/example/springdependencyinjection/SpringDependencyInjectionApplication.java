package com.example.springdependencyinjection;

import com.example.springdependencyinjection.command.Dispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDependencyInjectionApplication implements CommandLineRunner {

	@Autowired
	Dispatcher dispatcher;

	public static void main(String[] args) {
		SpringApplication.run(SpringDependencyInjectionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
