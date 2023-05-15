package io.github.logging.manager.filter;

import io.github.logging.manager.filter.logic.AccpetAllFilter;
import io.github.logging.manager.filter.logic.AllFilter;
import io.github.logging.manager.filter.logic.AnyFilter;
import io.github.logging.manager.filter.logic.DenyAllFilter;
import io.github.logging.manager.filter.logic.InvertFilter;

import java.util.List;

public class Filters {

    public static DenyAllFilter createDenyAllFilter() {
        return DenyAllFilter.INSTANCE;
    }

    public static Filter createAccpetAllFilter() {
        return AccpetAllFilter.INSTANCE;
    }

    public static AllFilter createAllFilter(List<Filter> filters) {
        return new AllFilter(filters);
    }

    public static AnyFilter createAnyFilter(List<Filter> filters) {
        return new AnyFilter(filters);
    }

    public static InvertFilter createInvertFilter(Filter filter) {
        return new InvertFilter(filter);
    }
}
