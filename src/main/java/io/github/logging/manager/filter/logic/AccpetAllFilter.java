package io.github.logging.manager.filter.logic;

import io.github.logging.manager.filter.Filter;

public class AccpetAllFilter implements Filter {

    private AccpetAllFilter() {
    }

    public static final Filter INSTANCE = new AccpetAllFilter();

    @Override
    public FilterReply filter(String loggerName, String level) {
        return FilterReply.ACCEPT;
    }
}
