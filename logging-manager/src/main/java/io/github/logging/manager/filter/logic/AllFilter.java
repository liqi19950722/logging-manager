package io.github.logging.manager.filter.logic;

import io.github.logging.manager.filter.Filter;

import java.util.List;
import java.util.Map;

public class AllFilter extends MultipleFilter {

    public AllFilter(List<Filter> filters) {
        super(filters);
    }

    @Override
    protected FilterReply judgeResult(Map<FilterReply, Long> result) {
        if (result.getOrDefault(FilterReply.DENY, 0L) > 0) {
            return FilterReply.DENY;
        }
        if (result.getOrDefault(FilterReply.NEUTRAL, 0L) > 0) {
            return FilterReply.NEUTRAL;
        }
        return FilterReply.ACCEPT;
    }
}
