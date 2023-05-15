package io.github.logging.manager.filter.logic;

import io.github.logging.manager.filter.Filter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class MultipleFilter implements Filter {
    protected final Filter[] filters;

    public MultipleFilter(List<Filter> filters) {
        this.filters = filters.toArray(new Filter[]{});
    }

    @Override
    public FilterReply filter(String loggerName, String level) {
        return judgeResult(doFilter(loggerName, level));
    }

    private Map<FilterReply, Long> doFilter(String loggerName, String level) {
        return Arrays.stream(filters).map(filter -> filter.filter(loggerName, level))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    protected abstract FilterReply judgeResult(Map<FilterReply, Long> result);

}
