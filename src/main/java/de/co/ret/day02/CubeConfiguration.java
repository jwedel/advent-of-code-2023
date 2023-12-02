package de.co.ret.day02;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public record CubeConfiguration(int red, int green, int blue ) {
    public static CubeConfiguration parse(String stringRevelation) {
        Map<Color, Integer> colorCounts = Arrays.stream(stringRevelation.trim().split(","))
                .map(String::trim)
                .filter(trimmedString -> !trimmedString.isEmpty())
                .map(ColorCount::parse)
                .collect(Collectors.toMap(colorCount -> colorCount.type, colorCount -> colorCount.count));

        return new CubeConfiguration(
                Optional.ofNullable(colorCounts.get(Color.RED)).orElse(0),
                Optional.ofNullable(colorCounts.get(Color.GREEN)).orElse(0),
                Optional.ofNullable(colorCounts.get(Color.BLUE)).orElse(0)
                );
    }

    record ColorCount(Color type, int count) {
        public static ColorCount parse(String colorString) {
            var tokens = Arrays.stream(colorString.split(" ")).toList();
            return new ColorCount(
                    Color.parse(tokens.getLast()),
                    Integer.parseInt(tokens.getFirst())
            );
        }
    }

    enum Color {
        RED,
        GREEN,
        BLUE;

        public static Color parse(String colorString) {
            return Color.valueOf(colorString.toUpperCase());
        }
    }
}
