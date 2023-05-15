package io.github.logging.manager.integration.servlet;

import io.github.logging.manager.LoggingManager;
import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;

public class ServletContextTest {

    private final ServletContext servletContext = mock(ServletContext.class);

    @Test
    public void should_add_ServletContextListener_onStartUp() throws ServletException {

        ServletContainerInitializer initializer = new LoggingManagerServletContextInitializer();
        initializer.onStartup(null, servletContext);

        verify(servletContext).addListener(any(LoggingManagerServletContextListener.class));
    }

    @Test
    public void should_create_LoggingManager_on_context_initialized() {

        try (var LOGGING_MANAGER = mockStatic(LoggingManager.class);) {
            var loggingManager = mock(LoggingManager.class);
            LOGGING_MANAGER.when(() -> LoggingManager.get(any(ClassLoader.class))).thenReturn(loggingManager);
            LOGGING_MANAGER.when(() -> LoggingManager.isPresent(any(ClassLoader.class), anyString())).thenReturn(true);

            var listener = new LoggingManagerServletContextListener();
            listener.contextInitialized(new ServletContextEvent(servletContext));

            LOGGING_MANAGER.verify(() -> LoggingManager.get(any(ClassLoader.class)));
        }
    }
}
