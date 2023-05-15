package io.github.logging.manager.integration.spring.util;

import io.github.logging.manager.LoggingManager;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class BeanFactoryHelper {

    private static final String LOGGING_MANAGER_BEAN_NAME = "loggingManager";

    public static void registerLoggingManager(ConfigurableListableBeanFactory configurableListableBeanFactory, LoggingManager loggingManager) {
        configurableListableBeanFactory.registerSingleton(LOGGING_MANAGER_BEAN_NAME, loggingManager);
    }
}
