package io.github.logging.manager.filter.logic;

import io.github.logging.manager.filter.Filter;

import java.util.List;
import java.util.Map;

public class AnyFilter extends MultipleFilter {

    public AnyFilter(List<Filter> filters) {
        super(filters);
    }

    @Override
    protected Filter.FilterReply judgeResult(Map<Filter.FilterReply, Long> result) {
        if (result.getOrDefault(Filter.FilterReply.ACCEPT, 0L) > 0) {
            return Filter.FilterReply.ACCEPT;
        }
        if (result.getOrDefault(Filter.FilterReply.NEUTRAL, 0L) > 0) {
            return Filter.FilterReply.NEUTRAL;
        }
        return Filter.FilterReply.DENY;
    }

}
