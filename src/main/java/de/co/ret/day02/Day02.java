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

        System.out.println(Sum.ofInts(games.getValidGameIds()));
    }
}
