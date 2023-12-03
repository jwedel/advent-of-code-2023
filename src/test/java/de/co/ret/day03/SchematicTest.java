package de.co.ret.day03;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SchematicTest {
    @Test
    void should_return_valid_part_numbers() {
        List<String> schematicLines = List.of(
                "467..114..",
                "...*......",
                "..35..633.",
                "......#...",
                "617*......",
                ".....+.58.",
                "..592.....",
                "......755.",
                "...$.*....",
                ".664.598.."
        );

        var schematic = Schematic.fromLines(schematicLines);

        assertThat(schematic.getPartIds()).containsOnly(467, 35, 633, 617, 592, 755, 664, 598);
    }

    @Test
    void should_return_gears() {
        List<String> schematicLines = List.of(
                "467..114..",
                "...*......",
                "..35..633.",
                "......#...",
                "617*......",
                ".....+.58.",
                "..592.....",
                "......755.",
                "...$.*....",
                ".664.598.."
        );

        var schematic = Schematic.fromLines(schematicLines);

        assertThat(schematic.getGears()).containsOnly(
                new Gears(467, 35),
                new Gears(755, 598));

    }
}