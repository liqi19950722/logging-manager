package io.github.logging.manager.integration.spring.boot.context.logging;

import io.github.logging.manager.LoggingManager;
import io.github.logging.manager.integration.spring.util.BeanFactoryHelper;
import io.github.logging.manager.integration.spring.util.EnvironmentHelper;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.boot.context.logging.LoggingApplicationListener;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.GenericApplicationListener;
import org.springframework.core.ResolvableType;

import java.util.Arrays;
import java.util.Objects;

public class LoggingManagerApplicationListener implements GenericApplicationListener {
    private static final Class<?>[] EVENT_TYPES = {ApplicationStartingEvent.class,
            ApplicationEnvironmentPreparedEvent.class, ApplicationPreparedEvent.class};

    @Override
    public boolean supportsEventType(ResolvableType resolvableType) {
        Class<?> rawClass = resolvableType.getRawClass();
        if (Objects.nonNull(rawClass)) {
            return Arrays.stream(EVENT_TYPES).anyMatch(eventType -> eventType.isAssignableFrom(rawClass));
        }
        return false;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationStartingEvent applicationStartingEvent) {
            this.loggingManager = LoggingManager.get(applicationStartingEvent.getSpringApplication().getClassLoader());
        }
        if (event instanceof ApplicationEnvironmentPreparedEvent applicationEnvironmentPreparedEvent) {
            var environment = applicationEnvironmentPreparedEvent.getEnvironment();
            var configuration = EnvironmentHelper.getProperty(environment);
            this.loggingManager.initialize(configuration);
        }

        if (event instanceof ApplicationPreparedEvent applicationPreparedEvent) {
            var applicationContext = applicationPreparedEvent.getApplicationContext();
            var configurableListableBeanFactory = applicationContext.getBeanFactory();
            BeanFactoryHelper.registerLoggingManager(configurableListableBeanFactory, loggingManager);
        }
    }

    @Override
    public int getOrder() {
        return LoggingApplicationListener.DEFAULT_ORDER + 1;
    }

    private LoggingManager loggingManager;

    public LoggingManager getLoggingManager() {
        return loggingManager;
    }
}
