package de.co.ret.day06;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class RaceStats {
    public static List<RaceRecord> fromLines(List<String> lines) {
        var times = Stream.of(lines
                        .get(0)
                        .replace("Time:", "")
                        .trim()
                        .split(" "))
                .map(String::trim)
                .filter(timeString -> !timeString.isEmpty())
                .map(Integer::parseInt)
                .toList();

        var distances = Stream.of(lines
                        .get(1)
                        .replace("Distance:", "")
                        .trim()
                        .split(" "))
                .map(String::trim)
                .filter(distanceString -> !distanceString.isEmpty())
                .map(Integer::parseInt)
                .toList();

        if (times.size() != distances.size()) {
            throw new IllegalArgumentException("Times and distances must be the same size");
        }

        return IntStream
                .range(0, times.size())
                .mapToObj(i -> new RaceRecord(times.get(i), distances.get(i)))
                .toList();
    }
}
