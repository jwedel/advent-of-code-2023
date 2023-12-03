package de.co.ret.day03;

import de.co.ret.common.FileHelper;
import de.co.ret.common.Sum;
import lombok.SneakyThrows;

import java.util.List;

public class Day03 {
    @SneakyThrows
    public static void main(String[] args) {
        List<String> lines = FileHelper.readAoCFile("03");
        var schematic = Schematic.fromLines(lines);

        List<Integer> validPartIds = schematic.getPartIds();
        validPartIds.forEach(System.out::println);
        System.out.println("Part 01: " + Sum.ofInts(validPartIds));

        List<Gears> gears = schematic.getGears();
        System.out.println("Part 02: " + Sum.ofInts(gears.stream().map(Gears::ratio).toList()));

    }
}
