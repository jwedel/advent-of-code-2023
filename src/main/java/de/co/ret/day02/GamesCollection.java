package de.co.ret.day02;

import java.util.List;

public record GamesCollection(List<Game> games) {

    public static GamesCollection fromLines(List<String> lines) {
        return new GamesCollection(lines.stream().map(Game::parse).toList());
    }

    public List<Integer> getValidGameIds(int totalRedCount, int totalGreenCount, int totalBlueCount) {
        return this.games.stream()
                .filter(game -> game.isValidForConfiguration(totalRedCount, totalGreenCount, totalBlueCount))
                .map(Game::id)
                .toList();
    }

    public List<CubeConfiguration> getMinimumConfigurations() {
        return this.games.stream()
                .map(Game::getMinimumConfiguration)
                .toList();
    }
}
