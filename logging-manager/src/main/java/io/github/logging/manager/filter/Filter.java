package io.github.logging.manager.filter;

public interface Filter {

    FilterReply filter(String loggerName, String level);

    enum FilterReply {
        DENY, NEUTRAL, ACCEPT;
    }

}
