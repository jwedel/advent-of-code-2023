package de.co.ret.day05;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AlmanacCategoryTest {
    public static final String CATEGORY_NAME = "cat";

    @Test
    void should_return_the_same_id_if_no_match() {
        AlmanacCategory category = new AlmanacCategory(CATEGORY_NAME, List.of(
                new AlmanacEntry(23, 43, 1)
        ));

        AlmanacMatch match = category.getDirectMapping(42);

        assertThat(match).isEqualTo(new AlmanacMatch(
                CATEGORY_NAME,
                42,
                42));
    }

    @Test
    void should_return_direct_match() {
        AlmanacCategory category = new AlmanacCategory(CATEGORY_NAME, List.of(
                new AlmanacEntry(23, 42, 1)
        ));

        AlmanacMatch match = category.getDirectMapping(42);

        assertThat(match).isEqualTo(new AlmanacMatch(
                CATEGORY_NAME,
                42,
                23));

    }

    @Test
    void should_return_direct_match_when_multiple_entries() {
        AlmanacCategory category = new AlmanacCategory(CATEGORY_NAME, List.of(
                new AlmanacEntry(1, 42, 3),
                new AlmanacEntry(4, 55, 15),
                new AlmanacEntry(18, 72, 5),
                new AlmanacEntry(23, 105, 1)
        ));

        AlmanacMatch match = category.getDirectMapping(75);

        assertThat(match).isEqualTo(new AlmanacMatch(
                CATEGORY_NAME,
                75,
                21));

    }

    @Test
    void should_return_direct_match_when_with_last_of_three_entries() {
        AlmanacCategory category = new AlmanacCategory(CATEGORY_NAME, List.of(
                new AlmanacEntry(1, 42, 3),
                new AlmanacEntry(4, 55, 15),
                new AlmanacEntry(18, 72, 5)
        ));

        AlmanacMatch match = category.getDirectMapping(75);

        assertThat(match).isEqualTo(new AlmanacMatch(
                CATEGORY_NAME,
                75,
                21));

    }
}