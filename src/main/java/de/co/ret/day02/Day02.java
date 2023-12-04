package de.co.ret.day02;

import de.co.ret.common.FileHelper;
import de.co.ret.common.Sum;
import lombok.SneakyThrows;

import java.util.List;

public class Day02 {
    @SneakyThrows
    public static void main(String[] args) {
        List<String> lines = FileHelper.readAoCFile("02");

        var games = GamesCollection.fromLines(lines);

        games.games().forEach(System.out::println);

        System.out.println("Part 01: " + Sum.ofInts(games.getValidGameIds(12, 13, 14)));
        System.out.println("Part 02: " + Sum.ofInts(games.getMinimumConfigurations().stream()
                .map(cubeConfig -> cubeConfig.red() * cubeConfig.green() * cubeConfig.blue())
                .toList()));
    }
}
