package io.github.logging.manager;

import io.github.logging.manager.logback.LogbackLoggingManager;

public interface LoggingManager {

    void initialize(String configLocation);

    static LoggingManager get(ClassLoader classLoader) {
        if (isPresent(classLoader, "ch.qos.logback.classic.LoggerContext")) {
            return LogbackLoggingManager.createLogbackLoggingManager(classLoader);
        }
        throw new IllegalStateException("No suitable logging system located");
    }

    static boolean isPresent(ClassLoader classLoader, String className) {
        try {
            Class.forName(className, false, classLoader);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
