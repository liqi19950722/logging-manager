package io.github.logging.manager.logback;

import ch.qos.logback.classic.LoggerContext;
import io.github.logging.manager.LoggingFilterFactory;
import io.github.logging.manager.LoggingManager;
import io.github.logging.manager.logback.context.LoggingManagerLogbackContextListener;
import io.github.logging.manager.logback.context.LoggingManagerTurboFilter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LogbackLoggingManagerTest {

    private LoggingManager loggingManager;
    private LoggerContext loggerContext;

    @BeforeEach
    public void setup() {
        var loggingFilterFactory = mock(LogbackLoggingFilterFactory.class);
        var filter1 = mock(LoggingManagerTurboFilter.class);
        var filter2 = mock(LoggingManagerTurboFilter.class);
        var filter3 = mock(LoggingManagerTurboFilter.class);
        when(loggingFilterFactory.createFilterForm(anyString())).thenReturn(List.of(filter1, filter2, filter3));

        loggingManager = LogbackLoggingManager.createLogbackLoggingManager(getClass().getClassLoader(),
                loggingFilterFactory);
        loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
    }

    @Test
    public void should_create_LogbackLoggingManager_by_class_loader() throws Exception {
        var loggingManager = LoggingManager.get(getClass().getClassLoader());
        Assertions.assertNotNull(loggingManager);
        Assertions.assertInstanceOf(LogbackLoggingManager.class, loggingManager);
    }

    @Test
    public void should_initialize_TurboFilter_into_LogContext() {
        loggingManager.initialize("");

        assertTrue(loggerContext.getTurboFilterList().stream()
                .anyMatch(filter -> filter instanceof LoggingManagerTurboFilter));

    }

    @Test
    public void should_initialize_LoggerContextListener_into_LogContext() {
        loggingManager.initialize("");


        assertTrue(loggerContext.getCopyOfListenerList().stream()
                .anyMatch(listener -> listener instanceof LoggingManagerLogbackContextListener));
    }

    @Test
    public void filter_should_be_consistent() {
        loggingManager.initialize("");

        var filterList = loggerContext.getTurboFilterList();
        var listenerList = loggerContext.getCopyOfListenerList().stream()
                .filter(listener -> listener instanceof LoggingManagerLogbackContextListener )
                .map(listener -> (LoggingManagerLogbackContextListener) listener)
                .toList();

        assertEquals(filterList.size(), listenerList.stream()
                .flatMap(listener -> listener.filters().stream()).toList().size());
    }
}
