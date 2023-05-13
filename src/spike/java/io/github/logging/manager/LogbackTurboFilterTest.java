package io.github.logging.manager;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.turbo.TurboFilter;
import ch.qos.logback.core.read.ListAppender;
import ch.qos.logback.core.spi.FilterReply;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LogbackTurboFilterTest {
    private static final Logger logger = LoggerFactory.getLogger(LogbackTurboFilterTest.class);
    ListAppender<ILoggingEvent> appender;

    LoggerContext loggerContext;
    ch.qos.logback.classic.Logger logbackTestLogger;

    @BeforeEach
    public void setUp() throws Exception {
        appender = new ListAppender<>();
        appender.start();

        var loggerFactory = LoggerFactory.getILoggerFactory();
        if (loggerFactory instanceof LoggerContext loggerContext) {
            this.loggerContext = loggerContext;
            this.logbackTestLogger = loggerContext.getLogger(LogbackTurboFilterTest.class);

            logbackTestLogger.addAppender(appender);
        }
    }

    @Test
    public void should_filter_log_via_TurboFilter() {
        TurboFilter filter = mock(TurboFilter.class);

        when(filter.decide(any(), same(logbackTestLogger), eq(Level.ERROR), any(), any(), any())).thenReturn(FilterReply.NEUTRAL);
        when(filter.decide(any(), same(logbackTestLogger), eq(Level.WARN), any(), any(), any())).thenReturn(FilterReply.NEUTRAL);
        when(filter.decide(any(), same(logbackTestLogger), eq(Level.INFO), any(), any(), any())).thenReturn(FilterReply.DENY);
        when(filter.decide(any(), same(logbackTestLogger), eq(Level.DEBUG), any(), any(), any())).thenReturn(FilterReply.DENY);
        when(filter.decide(any(), same(logbackTestLogger), eq(Level.TRACE), any(), any(), any())).thenReturn(FilterReply.DENY);
        loggerContext.addTurboFilter(filter);

        var msg = "test...";
        logger.error(msg);
        logger.warn(msg);
        logger.info(msg);
        logger.debug(msg);
        logger.trace(msg);
        var list = appender.list;
        Assertions.assertEquals(2, list.size());

        loggerContext.getTurboFilterList().remove(filter);
    }
}
