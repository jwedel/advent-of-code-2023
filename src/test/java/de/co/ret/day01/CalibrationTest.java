package de.co.ret.day01;

import de.co.ret.common.Sum;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CalibrationTest {
    @Test
    @SneakyThrows
    void should_return_0_for_empty_lines() {
        Calibration calibration = Calibration.fromLines(List.of());

        assertThat(Sum.ofInts(calibration.getValues())).isEqualTo(0);
    }

    @Test
    @SneakyThrows
    void should_return_142_for_test_lines() {
        Calibration calibration = Calibration.fromLines(List.of(
                "1abc2",
                "pqr3stu8vwx",
                "a1b2c3d4e5f",
                "treb7uchet"
        ));

        assertThat(Sum.ofInts(calibration.getValues())).isEqualTo(142);
    }

    @Test
    @SneakyThrows
    void should_return_142_for_test_lines_with_digit_words() {
        Calibration calibration = Calibration.fromLines(List.of(
                "two1nine",
                "eightwothree",
                "abcone2threexyz",
                "xtwone3four",
                "4nineeightseven2",
                "zoneight234",
                "7pqrstsixteen"
        ));

        assertThat(Sum.ofInts(calibration.getValues())).isEqualTo(281);
    }

    @Test
    void should_return_12_for_test_line() {
        assertThat(Calibration.cleanValue("1abc2")).isEqualTo("12");
    }

    @ParameterizedTest
    @CsvSource({
            "1abc2, 12",
            "pqr3stu8vwx, 38",
            "a1b2c3d4e5f, 15",
            "treb7uchet, 77",
            "two1nine, 29",
            "eightwothree, 83",
            "abcone2threexyz, 13",
            "xtwone3four, 24",
            "4nineeightseven2, 42",
            "zoneight234, 14",
            "7pqrstsixteen, 76",
            "oneight, 18",
    })
    void should_clean_also_number_words(String input, String expected) {
        assertThat(Calibration.cleanValue(input)).isEqualTo(expected);

    }

}