package de.co.ret.day02;

import java.util.List;

public class Games {
    private final List<Game> games;

    public List<Game> getGames() {
        return games;
    }

    public Games(List<Game> games) {
        this.games = games;
    }

    public static Games fromLines(List<String> lines) {
        return new Games(lines.stream().map(Game::parse).toList());
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
