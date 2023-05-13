package io.github.logging.manager.integration.spring.context;

import io.github.logging.manager.LoggingManager;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class LoggingManagerApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext context) {
        var configuration = context.getEnvironment().getProperty("logging.manager.configuration.location");

        var loggingManager = LoggingManager.get(getClass().getClassLoader());
        loggingManager.initialize(configuration);

        context.getBeanFactory().registerSingleton("loggingManager", loggingManager);
    }
}
