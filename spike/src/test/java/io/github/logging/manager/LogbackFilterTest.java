package io.github.logging.manager;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.filter.LevelFilter;
import ch.qos.logback.classic.filter.ThresholdFilter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.boolex.EvaluationException;
import ch.qos.logback.core.boolex.EventEvaluatorBase;
import ch.qos.logback.core.filter.EvaluatorFilter;
import ch.qos.logback.core.read.ListAppender;
import ch.qos.logback.core.spi.FilterReply;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class LogbackFilterTest {
    private static final Logger logger = LoggerFactory.getLogger(LogbackFilterTest.class);

    ListAppender<ILoggingEvent> appender;
    private ch.qos.logback.classic.Logger logbackTestLogger;

    @BeforeEach
    public void setup() {
        appender = new ListAppender<>();
        appender.start();

        var loggerFactory = LoggerFactory.getILoggerFactory();
        if (loggerFactory instanceof LoggerContext loggerContext) {
            this.logbackTestLogger = loggerContext.getLogger(LogbackFilterTest.class);

            logbackTestLogger.addAppender(appender);
        }
    }

    @AfterEach
    public void tearDown() {
        logbackTestLogger.detachAppender(appender);
    }

    private void logMessage() {
        var msg = "test...";
        logger.error(msg);
        logger.warn(msg);
        logger.info(msg);
        logger.debug(msg);
        logger.trace(msg);
    }

    @Test
    public void should_log_all_message() {
        logMessage();

        var loggingEventList = appender.list;

        Assertions.assertEquals(5, loggingEventList.size());
    }


    @Test
    public void should_filter_log_via_LevelFilter() {
        var levelFilter = new LevelFilter();
        levelFilter.setLevel(Level.INFO);
        levelFilter.setOnMatch(FilterReply.DENY);
        levelFilter.start();
        appender.addFilter(levelFilter);

        logMessage();

        var loggingEventList = appender.list;

        Assertions.assertEquals(4, loggingEventList.size());
    }


    @Test
    public void should_filter_log_via_ThresholdFilter() {
        var thresholdFilter = new ThresholdFilter();
        thresholdFilter.setLevel("INFO");
        thresholdFilter.start();
        appender.addFilter(thresholdFilter);

        logMessage();

        var loggingEventList = appender.list;

        Assertions.assertEquals(3, loggingEventList.size());
    }

    @Test
    public void should_filter_log_via_EvaluatorFilter() {
        var evaluatorFilter = new EvaluatorFilter<ILoggingEvent>();
        var eventEvaluator = new EventEvaluatorBase<ILoggingEvent>() {
            @Override
            public boolean evaluate(ILoggingEvent event) throws NullPointerException, EvaluationException {
                if (Objects.equals(event.getLoggerName(), LogbackFilterTest.class.getName())) {
                    return true;
                }
                return false;
            }
        };
        eventEvaluator.start();

        evaluatorFilter.setEvaluator(eventEvaluator);
        evaluatorFilter.setOnMatch(FilterReply.DENY);
        evaluatorFilter.start();
        appender.addFilter(evaluatorFilter);

        logMessage();

        var loggingEventList = appender.list;

        Assertions.assertEquals(0, loggingEventList.size());
    }


}
