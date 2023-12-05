package de.co.ret.day05;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AlmanacEntryTest {
    @Test
    void should_return_empty_optional_if_less_than_range_start() {
        AlmanacEntry entry = new AlmanacEntry(23, 42, 3);

        assertThat(entry.findMatch(41)).isEmpty();
    }

    @Test
    void should_return_empty_optional_if_greater_than_range_end() {
        AlmanacEntry entry = new AlmanacEntry(23, 42, 3);

        assertThat(entry.findMatch(45)).isEmpty();
    }

    @Test
    void should_return_correct_destination_id_if_within_range() {
        AlmanacEntry entry = new AlmanacEntry(23, 42, 3);

        assertThat(entry.findMatch(42)).contains(23L);
        assertThat(entry.findMatch(43)).contains(24L);
        assertThat(entry.findMatch(44)).contains(25L);
    }
}