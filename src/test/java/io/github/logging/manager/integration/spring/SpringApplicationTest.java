package io.github.logging.manager.integration.spring;

import io.github.logging.manager.LoggingManager;
import io.github.logging.manager.integration.spring.context.LoggingManagerApplicationContextInitializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringJUnitConfig(
        initializers = {LoggingManagerApplicationContextInitializer.class}
)
public class SpringApplicationTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void should_get_loggingManager_from_applicationContext() {
        var bean = applicationContext.getBean(LoggingManager.class);

        assertNotNull(bean);
    }
}
