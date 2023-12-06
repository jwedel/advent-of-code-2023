package de.co.ret.day05;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AlmanacTest {
    @Test
    void should_return_the_trace_for_a_given_source_id() {
        var categories = List.of(
                new AlmanacCategory("seed-to-soil", List.of(
                        new AlmanacEntry(50, 98, 2),
                        new AlmanacEntry(52, 50, 48))),

                new AlmanacCategory("soil-to-fertilizer", List.of(
                        new AlmanacEntry(0, 15, 37),
                        new AlmanacEntry(37, 52, 2),
                        new AlmanacEntry(39, 0, 15))),

                new AlmanacCategory("fertilizer-to-water", List.of(
                        new AlmanacEntry(49, 53, 8),
                        new AlmanacEntry(0, 11, 42),
                        new AlmanacEntry(42, 0, 7),
                        new AlmanacEntry(57, 7, 4))),

                new AlmanacCategory("water-to-light", List.of(
                        new AlmanacEntry(88, 18, 7),
                        new AlmanacEntry(18, 25, 70))),

                new AlmanacCategory("light-to-temperature", List.of(
                        new AlmanacEntry(45, 77, 23),
                        new AlmanacEntry(81, 45, 19),
                        new AlmanacEntry(68, 64, 13))),

                new AlmanacCategory("temperature-to-humidity", List.of(
                        new AlmanacEntry(0, 69, 1),
                        new AlmanacEntry(1, 0, 69))),

                new AlmanacCategory("humidity-to-location", List.of(
                        new AlmanacEntry(60, 56, 37),
                        new AlmanacEntry(56, 93, 4)))
        );

        Almanac almanac = new Almanac(categories);

        assertThat(almanac.getDirectMapping(79)).isEqualTo(
                List.of(
                        new AlmanacMatch("seed-to-soil", 79, 81),
                        new AlmanacMatch("soil-to-fertilizer", 81, 81),
                        new AlmanacMatch("fertilizer-to-water", 81, 81),
                        new AlmanacMatch("water-to-light", 81, 74),
                        new AlmanacMatch("light-to-temperature", 74, 78),
                        new AlmanacMatch("temperature-to-humidity", 78, 78),
                        new AlmanacMatch("humidity-to-location", 78, 82)
                ));
        assertThat(almanac.getLastDestinationId(79)).isEqualTo(82);

        assertThat(almanac.getDirectMapping(14)).isEqualTo(
                List.of(
                        new AlmanacMatch("seed-to-soil", 14, 14),
                        new AlmanacMatch("soil-to-fertilizer", 14, 53),
                        new AlmanacMatch("fertilizer-to-water", 53, 49),
                        new AlmanacMatch("water-to-light", 49, 42),
                        new AlmanacMatch("light-to-temperature", 42, 42),
                        new AlmanacMatch("temperature-to-humidity", 42, 43),
                        new AlmanacMatch("humidity-to-location", 43, 43)
                ));

        assertThat(almanac.getLastDestinationId(14)).isEqualTo(43);


        assertThat(almanac.getDirectMapping(55)).isEqualTo(
                List.of(
                        new AlmanacMatch("seed-to-soil", 55, 57),
                        new AlmanacMatch("soil-to-fertilizer", 57, 57),
                        new AlmanacMatch("fertilizer-to-water", 57, 53),
                        new AlmanacMatch("water-to-light", 53, 46),
                        new AlmanacMatch("light-to-temperature", 46, 82),
                        new AlmanacMatch("temperature-to-humidity", 82, 82),
                        new AlmanacMatch("humidity-to-location", 82, 86)
                ));

        assertThat(almanac.getLastDestinationId(55)).isEqualTo(86);


        assertThat(almanac.getDirectMapping(13)).isEqualTo(
                List.of(
                        new AlmanacMatch("seed-to-soil", 13, 13),
                        new AlmanacMatch("soil-to-fertilizer", 13, 52),
                        new AlmanacMatch("fertilizer-to-water", 52, 41),
                        new AlmanacMatch("water-to-light", 41, 34),
                        new AlmanacMatch("light-to-temperature", 34, 34),
                        new AlmanacMatch("temperature-to-humidity", 34, 35),
                        new AlmanacMatch("humidity-to-location", 35, 35)
                ));

        assertThat(almanac.getLastDestinationId(13)).isEqualTo(35);
    }
}