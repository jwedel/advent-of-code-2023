package de.co.ret.day08;

import de.co.ret.common.FileHelper;
import lombok.SneakyThrows;

import java.util.List;

public class Day08 {
    @SneakyThrows
    public static void main(String[] args) {
        List<String> lines = FileHelper.readAoCFile("08");

        MapDocument mapDocument = MapDocument.fromLines(lines);

        System.out.println("Day 08, Part 1: " + mapDocument.stepsToWalkTo("ZZZ"));
    }
}
