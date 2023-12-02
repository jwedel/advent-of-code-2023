package de.co.ret.day02;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public record Game(int id, List<CubeRevelation> revelations) {
    public static Game parse(String line) {
        var tokens = Stream.of(line.split(":")).map(String::trim).toList();

        return new Game(
                toGameId(tokens.getFirst()),
                toCubeRevelations(tokens.getLast()));
    }

    private static List<CubeRevelation> toCubeRevelations(String revelationsString) {
        return Arrays.stream(revelationsString.split(";"))
                .map(String::trim)
                .map(CubeRevelation::parse)
                .toList();
    }

    private static int toGameId(String gameIdString) {
        return Integer.parseInt(gameIdString.split(" ")[1]);
    }

    public boolean isValidForConfiguration(int totalRedCount, int totalGreenCount, int totalBlueCount) {
        int maxRedCount = 0;
        int maxGreenCount = 0;
        int maxBlueCount = 0;

        for (CubeRevelation revelation : this.revelations) {
            maxRedCount = Math.max(revelation.red(), maxRedCount);
            maxGreenCount = Math.max(revelation.green(), maxGreenCount);
            maxBlueCount = Math.max(revelation.blue(), maxBlueCount);
        }

        return maxRedCount <= totalRedCount && maxGreenCount <= totalGreenCount && maxBlueCount <= totalBlueCount;
    }
}
