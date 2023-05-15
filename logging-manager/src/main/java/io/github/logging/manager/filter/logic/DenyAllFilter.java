package io.github.logging.manager.filter.logic;

import io.github.logging.manager.filter.Filter;

public class DenyAllFilter implements Filter {
    private DenyAllFilter() {
    }

    public static final DenyAllFilter INSTANCE = new DenyAllFilter();

    @Override
    public FilterReply filter(String loggerName, String level) {
        return FilterReply.DENY;
    }
}
