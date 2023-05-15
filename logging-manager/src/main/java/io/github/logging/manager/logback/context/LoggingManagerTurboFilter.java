package io.github.logging.manager.logback.context;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.turbo.TurboFilter;
import ch.qos.logback.core.spi.FilterReply;
import io.github.logging.manager.filter.Filter;
import org.slf4j.Marker;

public class LoggingManagerTurboFilter extends TurboFilter {

    private final Filter filter;
    public LoggingManagerTurboFilter(String name, Filter filter) {
        setName(name);
        this.filter = filter;
    }

    @Override
    public FilterReply decide(Marker marker, Logger logger, Level level, String format, Object[] params, Throwable t) {
        return null;
    }
}
