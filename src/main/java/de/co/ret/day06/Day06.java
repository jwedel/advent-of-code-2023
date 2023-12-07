package de.co.ret.day06;

import de.co.ret.common.FileHelper;
import lombok.SneakyThrows;

import java.util.List;

public class Day06 {
    @SneakyThrows
    public static void main(String[] args) {
        List<String> lines = FileHelper.readAoCFile("06");

        System.out.println("Result Day 06, Part 2: " + RaceStats.fromLines(lines)
                .calculateWinningDistances()
                .size());

    }
}
