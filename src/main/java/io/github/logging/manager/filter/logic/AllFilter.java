package io.github.logging.manager.filter.logic;

import io.github.logging.manager.filter.Filter;

import java.util.List;
import java.util.Map;

public class AllFilter extends MultipleFilter {

    public AllFilter(List<Filter> filters) {
        super(filters);
    }

    @Override
    protected Filter.FilterReply judgeResult(Map<Filter.FilterReply, Long> result) {
        if (result.getOrDefault(Filter.FilterReply.DENY, 0L) > 0) {
            return Filter.FilterReply.DENY;
        }
        if (result.getOrDefault(Filter.FilterReply.NEUTRAL, 0L) > 0) {
            return Filter.FilterReply.NEUTRAL;
        }
        return Filter.FilterReply.ACCEPT;
    }
}
