package de.co.ret.day04;

import de.co.ret.common.FileHelper;
import lombok.SneakyThrows;

import java.util.List;

public class Day04 {
    @SneakyThrows
    public static void main(String[] args) {
        List<String> lines = FileHelper.readAoCFile("04");

        CardStack cardStack = CardStack.fromLines(lines);

        System.out.println("Part 01: " + cardStack.calculateTotalPoints());
        System.out.println("Part 02: " + cardStack.calculateScratchCardInstances());
    }
}
