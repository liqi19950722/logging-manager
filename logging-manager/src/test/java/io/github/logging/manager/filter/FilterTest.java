package io.github.logging.manager.filter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FilterTest {
    String loggerName = AcceptAllTest.class.getName();
    String level = "INFO";
    Filter accpetFilter;
    Filter denyFilter;
    Filter neutralFilter;

    @BeforeEach
    public void setup() {
        accpetFilter = mock(Filter.class);
        when(accpetFilter.filter(any(), any())).thenReturn(Filter.FilterReply.ACCEPT);

        denyFilter = mock(Filter.class);
        when(denyFilter.filter(any(), any())).thenReturn(Filter.FilterReply.DENY);

        neutralFilter = mock(Filter.class);
        when(neutralFilter.filter(any(), any())).thenReturn(Filter.FilterReply.NEUTRAL);
    }

    @Nested
    public class AcceptAllTest {

        @Test
        public void accept_all() {

            Filter filter = Filters.createAccpetAllFilter();

            var filterReply = filter.filter(loggerName, level);

            Assertions.assertEquals(Filter.FilterReply.ACCEPT, filterReply);
        }
    }

    @Nested
    public class DenyAllTest {

        @Test
        public void deny_all() {
            Filter filter = Filters.createDenyAllFilter();

            var filterReply = filter.filter(loggerName, level);

            Assertions.assertEquals(Filter.FilterReply.DENY, filterReply);
        }
    }

    @Nested
    public class AllTest {


        @Test
        public void all_filter_accepted_then_accept() {
            Filter filter = Filters.createAllFilter(List.of(accpetFilter, accpetFilter, accpetFilter));

            var filterReply = filter.filter(loggerName, level);

            Assertions.assertEquals(Filter.FilterReply.ACCEPT, filterReply);
        }

        @Test
        public void exist_filter_deny_then_deny() {
            Filter filter = Filters.createAllFilter(List.of(denyFilter, denyFilter, accpetFilter));

            var filterReply = filter.filter(loggerName, level);

            Assertions.assertEquals(Filter.FilterReply.DENY, filterReply);
        }

        @Test
        public void exist_filter_neutral_then_neutral() {
            Filter filter = Filters.createAllFilter(List.of(neutralFilter, accpetFilter, accpetFilter));

            var filterReply = filter.filter(loggerName, level);

            Assertions.assertEquals(Filter.FilterReply.NEUTRAL, filterReply);
        }

        @Test
        public void exist_filter_deny_neutral_then_deny() {
            Filter filter = Filters.createAllFilter(List.of(neutralFilter, denyFilter, accpetFilter));

            var filterReply = filter.filter(loggerName, level);

            Assertions.assertEquals(Filter.FilterReply.DENY, filterReply);
        }
    }


    @Nested
    public class AnyTest {

        @Test
        public void when_all_filter_deny_then_deny() {
            Filter filter = Filters.createAnyFilter(List.of(denyFilter, denyFilter, denyFilter));

            var filterReply = filter.filter(loggerName, level);

            Assertions.assertEquals(Filter.FilterReply.DENY, filterReply);
        }

        @Test
        public void exists_one_filter_accept_then_accept() {
            Filter filter = Filters.createAnyFilter(List.of(denyFilter, accpetFilter, denyFilter));

            var filterReply = filter.filter(loggerName, level);

            Assertions.assertEquals(Filter.FilterReply.ACCEPT, filterReply);
        }

        @Test
        public void exists_one_filter_neutral_then_neutral() {
            Filter filter = Filters.createAnyFilter(List.of(neutralFilter, denyFilter, denyFilter));

            var filterReply = filter.filter(loggerName, level);

            Assertions.assertEquals(Filter.FilterReply.NEUTRAL, filterReply);
        }

        @Test
        public void exists_filter_neutral_accept_then_accept() {
            Filter filter = Filters.createAnyFilter(List.of(neutralFilter, accpetFilter, denyFilter));

            var filterReply = filter.filter(loggerName, level);

            Assertions.assertEquals(Filter.FilterReply.ACCEPT, filterReply);
        }

    }

    @Nested
    public class InvertTest {

        @Test
        public void when_filter_accept_then_deny() {
            Filter filter = Filters.createInvertFilter(accpetFilter);

            var filterReply = filter.filter(loggerName, level);

            Assertions.assertEquals(Filter.FilterReply.DENY, filterReply);
        }

        @Test
        public void when_filter_deny_then_accept() {
            Filter filter = Filters.createInvertFilter(denyFilter);

            var filterReply = filter.filter(loggerName, level);

            Assertions.assertEquals(Filter.FilterReply.ACCEPT, filterReply);
        }

        @Test
        public void when_filter_neutral_then_neutral() {
            Filter filter = Filters.createInvertFilter(neutralFilter);

            var filterReply = filter.filter(loggerName, level);

            Assertions.assertEquals(Filter.FilterReply.NEUTRAL, filterReply);
        }
    }
}
