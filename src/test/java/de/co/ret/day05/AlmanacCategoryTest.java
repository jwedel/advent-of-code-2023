package de.co.ret.day05;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AlmanacCategoryTest {

    public static final String CATEGORY_NAME = "cat";

    @Test
    void should_return_the_same_id_if_no_mappings() {
        AlmanacCategory category = new AlmanacCategory(CATEGORY_NAME, List.of());

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
}