package de.co.ret.day06;

import de.co.ret.common.FileHelper;
import lombok.SneakyThrows;

import java.util.List;

public class Day06 {
    @SneakyThrows
    public static void main(String[] args) {
        List<String> lines = FileHelper.readAoCFile("06");

        System.out.println("Result Day 05, Part 1: " + RaceStats.fromLines(lines)
                .parallelStream()
                .map(RaceRecord::calculateWinningDistances)
                .map(List::size)
                .reduce(1, (accumulator, element) -> accumulator * element));
    }
}
