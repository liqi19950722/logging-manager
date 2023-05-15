package io.github.logging.manager.logback.context;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.LoggerContextListener;
import ch.qos.logback.classic.turbo.TurboFilter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LoggingManagerLogbackContextListener implements LoggerContextListener {

    private final List<TurboFilter> filters = new ArrayList<>();

    @Override
    public boolean isResetResistant() {
        return true;
    }

    @Override
    public void onStart(LoggerContext context) {
        filters.forEach(filter -> {
            filter.setContext(context);
            context.addTurboFilter(filter);
        });
    }

    @Override
    public void onReset(LoggerContext context) {
        filters.forEach(context::addTurboFilter);
    }

    @Override
    public void onStop(LoggerContext context) {

    }

    @Override
    public void onLevelChange(Logger logger, Level level) {

    }

    public void addFilter(TurboFilter filter) {
        filters.add(filter);
    }

    public List<TurboFilter> filters() {
        return Collections.unmodifiableList(filters);
    }
}
