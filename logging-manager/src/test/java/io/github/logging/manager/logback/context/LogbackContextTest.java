package io.github.logging.manager.logback.context;

import ch.qos.logback.classic.LoggerContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;

public class LogbackContextTest {

    LoggingManagerTurboFilter filter;
    LoggerContext loggerContext;

    @BeforeEach
    public void setup() {
        filter = mock(LoggingManagerTurboFilter.class);
        loggerContext = new LoggerContext();
        var listener = new LoggingManagerLogbackContextListener();
        listener.addFilter(filter);
        loggerContext.addListener(listener);
    }

    @Test
    public void should_add_TurboFilter_when_context_start() {
        loggerContext.start();

        var filters = loggerContext.getTurboFilterList();
        assertEquals(1, filters.size());
        assertSame(filter, filters.get(0));
    }

    @Test
    public void should_reset_TurboFilter_when_context_reset() {
        loggerContext.reset();

        var filters = loggerContext.getTurboFilterList();
        assertEquals(1, filters.size());
        assertSame(filter, filters.get(0));
    }
}
