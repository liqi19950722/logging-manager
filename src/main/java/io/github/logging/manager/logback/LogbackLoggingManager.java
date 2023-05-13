package io.github.logging.manager.logback;

import ch.qos.logback.classic.LoggerContext;
import io.github.logging.manager.LoggingManager;
import io.github.logging.manager.logback.context.LoggingManagerLogbackContextListener;
import org.slf4j.LoggerFactory;

public class LogbackLoggingManager implements LoggingManager {

    private final ClassLoader classLoader;

    private LogbackLoggingFilterFactory loggingFilterFactory;

    private LogbackLoggingManager(ClassLoader classLoader, LogbackLoggingFilterFactory loggingFilterFactory) {
        this.classLoader = classLoader;
        this.loggingFilterFactory = loggingFilterFactory;
    }

    public static LogbackLoggingManager createLogbackLoggingManager(ClassLoader classLoader) {
        return new LogbackLoggingManager(classLoader, null);
    }

    public static LogbackLoggingManager createLogbackLoggingManager(ClassLoader classLoader, LogbackLoggingFilterFactory loggingFilterFactory) {
        return new LogbackLoggingManager(classLoader, loggingFilterFactory);
    }

    @Override
    public void initialize(String configLocation) {
        var loggerFactory = LoggerFactory.getILoggerFactory();
        if (loggerFactory instanceof LoggerContext loggerContext) {
            var listener = new LoggingManagerLogbackContextListener();

            var filters = loggingFilterFactory.createFilterForm(configLocation);

            filters.forEach(filter -> {
                loggerContext.addTurboFilter(filter);
                listener.addFilter(filter);
            });

            loggerContext.addListener(listener);
        }
    }
}
