package de.co.ret.day04;

import de.co.ret.common.FileHelper;
import de.co.ret.common.Sum;
import de.co.ret.day03.Gears;
import lombok.SneakyThrows;

import java.util.List;

public class Day04 {
    @SneakyThrows
    public static void main(String[] args) {
        List<String> lines = FileHelper.readAoCFile("04");

        lines.forEach(System.out::println);

        CardStack cardStack = CardStack.fromLines(lines);

        System.out.println("Part 01: " + cardStack.calculateTotalPoints());
    }
}
