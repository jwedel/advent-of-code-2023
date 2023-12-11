package de.co.ret.day09;

import de.co.ret.common.FileHelper;
import de.co.ret.common.Sum;
import lombok.SneakyThrows;

import java.util.List;

public class Day09 {
    @SneakyThrows
    public static void main(String[] args) {
        List<String> lines = FileHelper.readAoCFile("09");

        System.out.println("Day 09, Part 1: " + Sum.ofLongs(lines.stream()
                .map(OASISMeasurements::parseLine)
                .map(OASISMeasurements::extrapolateNextValue)
                .toList()));
    }
}
