package de.co.ret.day05;

import de.co.ret.common.FileHelper;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static de.co.ret.day05.Day05.ParserState.*;

public class Day05 {
    public static final Pattern SEEDS_PATTERN = Pattern.compile("^seeds: (?<seeds>.*$)");
    public static final Pattern CATEGORY_PATTERN = Pattern.compile("^(?<categoryName>\\S+) map:$");

    @SneakyThrows
    public static void main(String[] args) {
        List<String> lines = FileHelper.readAoCFile("05");

        var results = fromLines(lines);
        var seeds = results.first();
        var almanac = results.second();

        var minimumLocationId = seeds.stream()
                .map(almanac::getDirectMapping)
                .map(List::getLast)
                .map(AlmanacMatch::destinationId)
                .reduce(Long.MAX_VALUE, Long::min);

        System.out.println("Day 05, Part 1: " + minimumLocationId);

        var seedRanges = SeedRanges.fromPairList(seeds);

        var minimumLocationIdPart2 = seedRanges.stream()
                .map(range -> LongStream.rangeClosed(
                                range.startId(),
                                range.getEndId())
                        .iterator())
                .map(almanac::findMinimumLocationId)
                .reduce(Long.MAX_VALUE, Long::min);

        System.out.println("Day 05, Part 2: " + minimumLocationIdPart2);
    }

    private static Stream<LongStream> toSeedStream(List<SeedRange> seedRanges) {
        return seedRanges.parallelStream()
                .map(seedRange -> LongStream
                        .range(seedRange.startId(), seedRange.startId() + seedRange.rangeLength()));
    }

    private static Tuple<List<Long>, Almanac> fromLines(List<String> lines) {
        var state = SEEDS;
        List<Long> seeds = null;
        String categoryName = null;
        List<AlmanacEntry> entries = null;
        List<AlmanacCategory> categories = new ArrayList<>();
        for (String line : lines) {
            switch (state) {
                case SEEDS -> {
                    var matcher = SEEDS_PATTERN.matcher(line);
                    if (matcher.matches()) {
                        seeds = toSeeds(matcher.group("seeds"));
                        state = CATEGORY;
                    }
                }
                case CATEGORY -> {
                    var matcher = CATEGORY_PATTERN.matcher(line);
                    if (matcher.matches()) {
                        categoryName = matcher.group("categoryName");
                        state = ENTRIES;
                        entries = new ArrayList<>();
                    }
                }
                case ENTRIES -> {
                    if (line.trim().isEmpty()) {
                        state = CATEGORY;
                        categories.add(new AlmanacCategory(categoryName, entries));
                    } else {
                        entries.add(AlmanacEntry.parse(line));
                    }
                }
            }
        }

        if (ENTRIES.equals(state)) {
            categories.add(new AlmanacCategory(categoryName, entries));
        }

        return new Tuple<>(seeds, new Almanac(categories));
    }

    private static List<Long> toSeeds(String seeds) {
        return Stream.of(seeds.trim().split(" "))
                .map(Long::parseLong)
                .toList();
    }

    enum ParserState {
        SEEDS,
        CATEGORY,
        ENTRIES
    }
}
