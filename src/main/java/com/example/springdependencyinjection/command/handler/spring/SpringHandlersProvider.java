package com.example.springdependencyinjection.command.handler.spring;


import com.example.springdependencyinjection.command.handler.CommandHandler;
import com.example.springdependencyinjection.command.HandlersProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Registers handlers for Spring and provides the required handler when needed
 * https://stackoverflow.com/questions/47915493/spring-5-programmatically-register-generic-bean
 */
@Component
public class SpringHandlersProvider implements HandlersProvider, ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ConfigurableListableBeanFactory beanFactory;

    private Map<Class<?>, String> handlers = new HashMap<>();

    @SuppressWarnings("unchecked")
	@Override
    public CommandHandler<Object, Object> getHandler(Object command) {
        String beanName = handlers.get(command.getClass());
        if (beanName == null) {
            throw new RuntimeException("command handler not found. Command class is " + command.getClass());
        }
        CommandHandler<Object, Object> handler = beanFactory.getBean(beanName, CommandHandler.class);
        return handler;
    }

    /**
     * On either initializing or refreshing the ApplicationContext.
     * Can get triggered multiple times as long as the context has not been closed.
     * */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        handlers.clear();
        String[] commandHandlersNames = beanFactory.getBeanNamesForType(CommandHandler.class);
        for (String beanName : commandHandlersNames) {
            BeanDefinition commandHandler = beanFactory.getBeanDefinition(beanName);
            try {
                Class<?> handlerClass = Class.forName(commandHandler.getBeanClassName());
                handlers.put(getHandledCommandType(handlerClass), beanName);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Returns e.g.
     *
     *  TestCommand from CommandHandler<TestCommand, Void>
     **/
    private Class<?> getHandledCommandType(Class<?> clazz) {
        Type[] genericInterfaces = clazz.getGenericInterfaces();
        ParameterizedType type = findByRawType(genericInterfaces, CommandHandler.class);
        return (Class<?>) type.getActualTypeArguments()[0]; // the TCommand
    }

    /**
     * Returns e.g.
     *
     *   <p> CommandHandler<TestCommand, Void> </p>
     *
     *   which is the raw type i.e. implemented interface
     **/
    private ParameterizedType findByRawType(Type[] genericInterfaces, Class<?> expectedRawType) {
        for (Type type : genericInterfaces) {
            if (type instanceof ParameterizedType) { // GenericType in C#
                ParameterizedType parametrized = (ParameterizedType) type;
                if (expectedRawType.equals(parametrized.getRawType())) {
                    return parametrized;
                }
            }
        }
        throw new RuntimeException();
    }
}
