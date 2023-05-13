package io.github.logging.manager.integration.spring.util;

import org.springframework.core.env.ConfigurableEnvironment;

public class EnvironmentHelper {

    private static final String LOGGING_MANAGER_CONFIG_LOCATION = "logging.manager.configuration.location";

    public static String getProperty(ConfigurableEnvironment environment) {
        return environment.getProperty(LOGGING_MANAGER_CONFIG_LOCATION);
    }
}
