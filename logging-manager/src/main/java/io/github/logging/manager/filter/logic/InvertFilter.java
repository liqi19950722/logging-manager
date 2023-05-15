package io.github.logging.manager.filter.logic;

import io.github.logging.manager.filter.Filter;

import java.util.Objects;

public class InvertFilter implements Filter {
    private final Filter filter;

    public InvertFilter(Filter filter) {
        this.filter = filter;
    }

    @Override
    public FilterReply filter(String loggerName, String level) {
        var filterReply = filter.filter(loggerName, level);
        if (Objects.equals(filterReply, FilterReply.ACCEPT)) {
            return FilterReply.DENY;
        }
        if (Objects.equals(filterReply, FilterReply.DENY)) {
            return FilterReply.ACCEPT;
        }
        return FilterReply.NEUTRAL;
    }
}
