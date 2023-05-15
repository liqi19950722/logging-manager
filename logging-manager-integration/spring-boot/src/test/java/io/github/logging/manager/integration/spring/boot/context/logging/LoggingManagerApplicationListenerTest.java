package io.github.logging.manager.integration.spring.boot.context.logging;

import io.github.logging.manager.LoggingManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.DefaultBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoggingManagerApplicationListenerTest {

    LoggingManagerApplicationListener listener = new LoggingManagerApplicationListener();
    private SpringApplication application;
    private DefaultBootstrapContext bootstrapContext;
    private ConfigurableEnvironment environment;
    private ConfigurableApplicationContext context;

    @BeforeEach
    void setUp() {
        application = new SpringApplication();
        bootstrapContext = new DefaultBootstrapContext();
        environment = new StandardEnvironment();
        context = new AnnotationConfigApplicationContext();
        context.refresh();
    }

    @Test
    public void should_create_logging_manager_on_ApplicationStartingEvent() {
        triggerApplicationStartingEvent();

        assertNotNull(listener.getLoggingManager());
    }

    @Test
    public void should_initialize_logging_manager_on_ApplicationEnvironmentPreparedEvent() {
        triggerApplicationStartingEvent();

        var applicationEnvironmentPreparedEvent = new ApplicationEnvironmentPreparedEvent(bootstrapContext, application, null, environment);

        listener.onApplicationEvent(applicationEnvironmentPreparedEvent);

        assertTrue(listener.getLoggingManager().isInitialized());
    }

    @Test
    public void should_register_logging_manager_as_bean_on_ApplicationPreparedEvent() {
        triggerApplicationStartingEvent();

        ApplicationPreparedEvent applicationPreparedEvent = new ApplicationPreparedEvent(application, null, context);
        listener.onApplicationEvent(applicationPreparedEvent);

        assertNotNull(context.getBean(LoggingManager.class));
    }

    private void triggerApplicationStartingEvent() {
        var applicationStartingEvent = new ApplicationStartingEvent(bootstrapContext, application, null);

        listener.onApplicationEvent(applicationStartingEvent);
    }
}
