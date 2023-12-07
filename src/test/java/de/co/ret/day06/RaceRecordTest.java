package de.co.ret.day06;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RaceRecordTest {
    @Test
    void should_return_0_for_0() {
        var record = new RaceRecord(0, 0);

        var options = record.calculateAllRaceOptions();

        assertThat(options).containsOnly(0);
    }

    @Test
    void should_return_4_options_for_3_millis() {
        var record = new RaceRecord(3, 3);

        var options = record.calculateAllRaceOptions();

        assertThat(options).containsOnly(
                0,
                2,
                2,
                0
        );
    }

    @Test
    void should_return_8_options_for_7_millis() {
        var record = new RaceRecord(7, 3);

        var options = record.calculateAllRaceOptions();

        assertThat(options).containsOnly(
                0,
                6,
                10,
                12,
                12,
                10,
                6,
                0
        );
    }

    @Test
    void should_return_winning_options() {
        var record = new RaceRecord(7, 9);

        var options = record.calculateWinningDistances();

        assertThat(options).containsOnly(
                10,
                12,
                12,
                10
        );
    }
}