package de.co.ret.day05;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;


public class SeedRanges {
    public static List<SeedRange> fromPairList(List<Long> seeds) {
        return IntStream.range(0, seeds.size() / 2)
                .mapToObj(index -> new SeedRange(
                        seeds.get(2 * index),
                        seeds.get(2 * index + 1)))
                .toList();
    }

    public static List<SeedRange> mergeRanges(List<SeedRange> seedRanges) {
        if (seedRanges.isEmpty()) {
            return seedRanges;
        }

        var intervals = seedRanges.stream()
                .map(range -> new Tuple<>(range.startId(), range.startId() + range.rangeLength() - 1))
                .sorted(Comparator.comparingLong(Tuple<Long, Long>::first))
                .toList();

        List<SeedRange> mergedRanges = new ArrayList<>();
        Tuple<Long, Long> lastInterval = null;

        for (Tuple<Long, Long> currentInterval : intervals) {
            if (lastInterval == null) {
                lastInterval = currentInterval;
                continue;
            }

            if (lastInterval.second() >= currentInterval.first()) {
                lastInterval = new Tuple<>(lastInterval.first(), Math.max(lastInterval.second(), currentInterval.second()));
            } else {
                mergedRanges.add(SeedRange.fromInterval(lastInterval.first(), lastInterval.second()));
                lastInterval = currentInterval;
            }
        }
        mergedRanges.add(SeedRange.fromInterval(lastInterval.first(), lastInterval.second()));

        return mergedRanges;
    }

}
