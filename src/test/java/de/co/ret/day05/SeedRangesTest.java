package de.co.ret.day05;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SeedRangesTest {
    @Test
    void should_return_pairs_from_list() {
        List<SeedRange> seedRanges = SeedRanges.fromPairList(List.of(1L, 2L, 3L, 4L, 5L, 6L));

        assertThat(seedRanges).containsOnly(
                new SeedRange(1, 2),
                new SeedRange(3, 4),
                new SeedRange(5, 6)
        );
    }

    @Test
    void should_return_empty_list_when_input_is_empty() {
        List<SeedRange> seedRanges = SeedRanges.mergeRanges(List.of());

        assertThat(seedRanges).isEmpty();
    }

    @Test
    void should_keep_ranges_when_no_overlap() {
        List<SeedRange> seedRanges = SeedRanges.mergeRanges(List.of(
                new SeedRange(1, 2),
                new SeedRange(5, 6)
        ));

        assertThat(seedRanges).containsOnly(
                new SeedRange(1, 2),
                new SeedRange(5, 6)
        );
    }

    @Test
    void should_not_merge_when_adjacent_ranges() {
        List<SeedRange> seedRanges = SeedRanges.mergeRanges(List.of(
                new SeedRange(1, 2),
                new SeedRange(3, 2),
                new SeedRange(5, 2)
        ));

        assertThat(seedRanges).containsOnly(
                new SeedRange(1, 2),
                new SeedRange(3, 2),
                new SeedRange(5, 2)
        );
    }

    @Test
    void should_merge_when_overlapping_by_exactly_one() {
        List<SeedRange> seedRanges = SeedRanges.mergeRanges(List.of(
                new SeedRange(1, 3),
                new SeedRange(3, 3),
                new SeedRange(5, 3)
        ));

        assertThat(seedRanges).containsOnly(
                new SeedRange(1, 7)
        );
    }

    @Test
    void should_merge_when_overlapping() {
        List<SeedRange> seedRanges = SeedRanges.mergeRanges(List.of(
                new SeedRange(1, 5),
                new SeedRange(3, 5)
        ));

        assertThat(seedRanges).containsOnly(
                new SeedRange(1, 7)
        );
    }

    @Test
    void should_merge_when_one_including_all_other_ranges() {
        List<SeedRange> seedRanges = SeedRanges.mergeRanges(List.of(
                new SeedRange(1, 7),
                new SeedRange(3, 2),
                new SeedRange(5, 2)
        ));

        assertThat(seedRanges).containsOnly(
                new SeedRange(1, 7)
        );
    }
}