package io.github.logging.manager;

import java.util.List;

public interface LoggingFilterFactory<T> {

    List<T> createFilterForm(String location);
}
