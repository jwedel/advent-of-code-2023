package de.co.ret.day09;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OASISMeasurementsTest {
    @Test
    void should_parse_measurements() {
        OASISMeasurements measurements = OASISMeasurements.parseLine("-1 0 1 2");

        assertThat(measurements).isEqualTo(new OASISMeasurements(List.of(
                -1L, 0L, 1L, 2L
        )));
    }

    @Nested
    @DisplayName("should extrapolate next number")
    class Extrapolation {

        @Test
        void when_same_number() {
            OASISMeasurements measurements = OASISMeasurements.parseLine("1 1 1 1");
            assertThat(measurements.extrapolateNextValue()).isEqualTo(1);
        }

        @Test
        void when_number_diff_is_one() {
            OASISMeasurements measurements = OASISMeasurements.parseLine("1 2 3");
            assertThat(measurements.extrapolateNextValue()).isEqualTo(4);
        }

        @Test
        void when_diff_is_two() {
            OASISMeasurements measurements = OASISMeasurements.parseLine("1 3 5");
            assertThat(measurements.extrapolateNextValue()).isEqualTo(7);
        }

        @Test
        void when_number_increasing_diff() {
            OASISMeasurements measurements = OASISMeasurements.parseLine("1 2 4 7");
            assertThat(measurements.extrapolateNextValue()).isEqualTo(11);
        }

        @Test
        void when_negative_numbers() {
            OASISMeasurements measurements = OASISMeasurements.parseLine("-5 -3 0 4");
            assertThat(measurements.extrapolateNextValue()).isEqualTo(9);
        }

        @Test
        void when_negative_diff() {
            OASISMeasurements measurements = OASISMeasurements.parseLine("-1 -2 -4 -7");
            assertThat(measurements.extrapolateNextValue()).isEqualTo(-11);
        }

        @Test
        void when_numbers_from_examples() {
            assertThat(OASISMeasurements.parseLine("0 3 6 9 12 15").extrapolateNextValue())
                    .isEqualTo(18);

            assertThat(OASISMeasurements.parseLine("1 3 6 10 15 21").extrapolateNextValue())
                    .isEqualTo(28);

            assertThat(OASISMeasurements.parseLine("10 13 16 21 30 45").extrapolateNextValue())
                    .isEqualTo(68);
        }

        @Test
        void when_numbers_jump_between_positive_and_negative() {
            assertThat(OASISMeasurements.parseLine("7 23 56 112 206 369 661 1190 2131 3727 6237 9780 14024 17718 18228 11631 -5265 -28077 -27602 87844 547546")
                    .extrapolateNextValue()).isEqualTo(1858731);
        }
    }

}