package io.github.logging.manager.integration.spring.context;

import io.github.logging.manager.LoggingManager;
import io.github.logging.manager.integration.spring.util.BeanFactoryHelper;
import io.github.logging.manager.integration.spring.util.EnvironmentHelper;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class LoggingManagerApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext context) {
        var environment = context.getEnvironment();
        String configuration = EnvironmentHelper.getProperty(environment);

        var loggingManager = LoggingManager.get(getClass().getClassLoader());
        loggingManager.initialize(configuration);

        var configurableListableBeanFactory = context.getBeanFactory();
        BeanFactoryHelper.registerLoggingManager(configurableListableBeanFactory, loggingManager);
    }

}
