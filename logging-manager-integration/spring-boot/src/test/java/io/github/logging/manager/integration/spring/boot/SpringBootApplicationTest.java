package io.github.logging.manager.integration.spring.boot;

import io.github.logging.manager.LoggingManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(
        classes = {SpringBootApplicationTest.TestConfiguration.class},
        webEnvironment = SpringBootTest.WebEnvironment.NONE
)
public class SpringBootApplicationTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void should_get_loggingManager_from_applicationContext() {
        var bean = applicationContext.getBean(LoggingManager.class);

        assertNotNull(bean);
    }

    @SpringBootConfiguration
    static class TestConfiguration {

    }
}
