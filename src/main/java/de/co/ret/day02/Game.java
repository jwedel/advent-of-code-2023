package de.co.ret.day02;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public record Game(int id, List<CubeConfiguration> revelations) {
    public static Game parse(String line) {
        var tokens = Stream.of(line.split(":")).map(String::trim).toList();

        return new Game(
                toGameId(tokens.getFirst()),
                toCubeRevelations(tokens.getLast()));
    }

    private static List<CubeConfiguration> toCubeRevelations(String revelationsString) {
        return Arrays.stream(revelationsString.split(";"))
                .map(String::trim)
                .map(CubeConfiguration::parse)
                .toList();
    }

    private static int toGameId(String gameIdString) {
        return Integer.parseInt(gameIdString.split(" ")[1]);
    }

    public boolean isValidForConfiguration(int totalRedCount, int totalGreenCount, int totalBlueCount) {
        var minimumConfig = getMinimumConfiguration();

        return minimumConfig.red() <= totalRedCount && minimumConfig.green() <= totalGreenCount && minimumConfig.blue() <= totalBlueCount;
    }

    public CubeConfiguration getMinimumConfiguration() {
        int maxRedCount = 0;
        int maxGreenCount = 0;
        int maxBlueCount = 0;

        for (CubeConfiguration revelation : this.revelations) {
            maxRedCount = Math.max(revelation.red(), maxRedCount);
            maxGreenCount = Math.max(revelation.green(), maxGreenCount);
            maxBlueCount = Math.max(revelation.blue(), maxBlueCount);
        }

        return new CubeConfiguration(maxRedCount,maxGreenCount,maxBlueCount);
    }
}
