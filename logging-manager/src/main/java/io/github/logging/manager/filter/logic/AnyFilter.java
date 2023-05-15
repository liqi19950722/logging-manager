package io.github.logging.manager.filter.logic;

import io.github.logging.manager.filter.Filter;

import java.util.List;
import java.util.Map;

public class AnyFilter extends MultipleFilter {

    public AnyFilter(List<Filter> filters) {
        super(filters);
    }

    @Override
    protected FilterReply judgeResult(Map<FilterReply, Long> result) {
        if (result.getOrDefault(FilterReply.ACCEPT, 0L) > 0) {
            return FilterReply.ACCEPT;
        }
        if (result.getOrDefault(FilterReply.NEUTRAL, 0L) > 0) {
            return FilterReply.NEUTRAL;
        }
        return FilterReply.DENY;
    }

}
