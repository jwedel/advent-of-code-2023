package de.co.ret.day02;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CubeConfigurationTest {
    @Test
    void should_return_parsed_revelation_all_zero_for_empty_string() {
        var revelation = CubeConfiguration.parse("");

        assertThat(revelation.red()).isEqualTo(0);
        assertThat(revelation.green()).isEqualTo(0);
        assertThat(revelation.blue()).isEqualTo(0);
    }

    @Test
    void should_return_parsed_revelation_all_zero_for_all_0() {
        var revelation = CubeConfiguration.parse("0 blue, 0 red, 0 green");

        assertThat(revelation.red()).isEqualTo(0);
        assertThat(revelation.green()).isEqualTo(0);
        assertThat(revelation.blue()).isEqualTo(0);
    }
    @Test
    void should_return_parsed_revelation_with_no_green() {
        var revelation = CubeConfiguration.parse("3 blue, 4 red");

        assertThat(revelation.red()).isEqualTo(4);
        assertThat(revelation.green()).isEqualTo(0);
        assertThat(revelation.blue()).isEqualTo(3);
    }

    @Test
    void should_return_parsed_revelation() {
        var revelation = CubeConfiguration.parse("1 red, 2 green, 6 blue");

        assertThat(revelation.red()).isEqualTo(1);
        assertThat(revelation.green()).isEqualTo(2);
        assertThat(revelation.blue()).isEqualTo(6);
    }

    @Test
    void should_return_parsed_revelation_with_only_green() {
        var revelation = CubeConfiguration.parse("2 green");

        assertThat(revelation.red()).isEqualTo(0);
        assertThat(revelation.green()).isEqualTo(2);
        assertThat(revelation.blue()).isEqualTo(0);
    }
}