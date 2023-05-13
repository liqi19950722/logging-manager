package io.github.logging.manager.logback;

import io.github.logging.manager.LoggingFilterFactory;
import io.github.logging.manager.logback.context.LoggingManagerTurboFilter;

import java.util.List;

public class LogbackLoggingFilterFactory implements LoggingFilterFactory<LoggingManagerTurboFilter> {
    @Override
    public List<LoggingManagerTurboFilter> createFilterForm(String location) {
        return null;
    }
}
