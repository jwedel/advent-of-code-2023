package de.co.ret.day02;

import de.co.ret.common.FileHelper;
import de.co.ret.common.Sum;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Day02 {
    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> lines = FileHelper.readAoCFile("02");

        var games = Games.fromLines(lines);

        games.getGames().forEach(System.out::println);

        System.out.println("Part 01: " + Sum.ofInts(games.getValidGameIds(12, 13, 14)));
        System.out.println("Part 02: " + Sum.ofInts(games.getMinimumConfigurations().stream()
                .map(cubeConfig -> cubeConfig.red() * cubeConfig.green() * cubeConfig.blue())
                .toList()));
    }
}
