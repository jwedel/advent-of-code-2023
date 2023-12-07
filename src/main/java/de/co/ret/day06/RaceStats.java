package de.co.ret.day06;

import java.util.List;
import java.util.stream.Stream;

public class RaceStats {
    public static RaceRecord fromLines(List<String> lines) {
        var time = Integer.parseInt(Stream.of(lines
                        .get(0)
                        .replace("Time:", "")
                        .trim()
                        .split(" "))
                .map(String::trim)
                .filter(timeString -> !timeString.isEmpty())
                .reduce("", String::concat));

        var recordDistance = Long.parseLong(Stream.of(lines
                        .get(1)
                        .replace("Distance:", "")
                        .trim()
                        .split(" "))
                .map(String::trim)
                .filter(timeString -> !timeString.isEmpty())
                .reduce("", String::concat));

        return new RaceRecord(time, recordDistance);
    }
}
