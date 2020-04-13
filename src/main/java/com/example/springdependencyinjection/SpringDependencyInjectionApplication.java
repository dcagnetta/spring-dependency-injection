package com.example.springdependencyinjection;

import com.example.springdependencyinjection.command.Dispatcher;
import com.example.springdependencyinjection.sample.TestClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class SpringDependencyInjectionApplication implements CommandLineRunner {

	@Autowired
	Dispatcher dispatcher;

	@Autowired
	List<String> nameList;

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringDependencyInjectionApplication.class, args);
		ConfigurableBeanFactory beanFactory=applicationContext.getBeanFactory();

		// Lets register a singleton bean
		beanFactory.registerSingleton("testBean", new TestClass<String>());
		TestClass<String> testObj =  applicationContext.getBean(TestClass.class);
		testObj.testMethod("hello");


	}

	@Override
	public void run(String... args) throws Exception {
		for (String name : nameList) {
			System.out.println(name + "\t");
		}

	}
}
