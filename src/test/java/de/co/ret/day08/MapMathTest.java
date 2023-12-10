package de.co.ret.day08;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MapMathTest {
    @Test
    void least_common_multiple_single_number() {
        assertThat(MapMath.findLCM(List.of(1L))).isEqualTo(1);
        assertThat(MapMath.findLCM(List.of(15L))).isEqualTo(15);
    }

    @Test
    void least_common_multiple_two() {
        assertThat(MapMath.findLCM(List.of(1L, 15L))).isEqualTo(15);
        assertThat(MapMath.findLCM(List.of(15L, 15L))).isEqualTo(15);
        assertThat(MapMath.findLCM(List.of(2L, 15L))).isEqualTo(30);
        assertThat(MapMath.findLCM(List.of(3L, 15L))).isEqualTo(15);
    }

    @Test
    void least_common_multiple_three() {
        assertThat(MapMath.findLCM(List.of(2L, 15L, 36L))).isEqualTo(180);
    }
}