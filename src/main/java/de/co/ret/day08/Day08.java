package de.co.ret.day08;

import de.co.ret.common.FileHelper;
import lombok.SneakyThrows;

import java.util.List;

public class Day08 {
    @SneakyThrows
    public static void main(String[] args) {
        List<String> lines = FileHelper.readAoCFile("08");

        MapDocument mapDocumentPart1 = MapDocument.fromLines(
                lines,
                (nodeName -> nodeName.equals("AAA")));

        //System.out.println("Day 08, Part 1: " + mapDocumentPart1.stepsToWalkTo("ZZZ"::equals));

        MapDocument mapDocumentPart2 = MapDocument.fromLines(
                lines,
                (nodeName -> nodeName.endsWith("A")));

        System.out.println("Day 08, Part 2: " + mapDocumentPart2.stepsToWalkTo(nodeName -> nodeName.endsWith("Z")));
    }
}
