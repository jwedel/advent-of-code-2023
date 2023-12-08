package de.co.ret.day07;

import de.co.ret.common.FileHelper;
import lombok.SneakyThrows;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class Day07 {
    @SneakyThrows
    public static void main(String[] args) {
        List<String> lines = FileHelper.readAoCFile("07");

        var handBids = CamelCards.fromLines(lines)
                .stream()
                .sorted(Comparator.comparing(HandBid::hand))
                .toList();

        handBids.forEach(System.out::println);


        System.out.println("Day 07, Part 1: " + IntStream.range(0, handBids.size())
                .mapToLong(index -> (1L + index) * handBids.get(index).bid())
                .reduce(Long::sum).orElse(0));
    }
}
