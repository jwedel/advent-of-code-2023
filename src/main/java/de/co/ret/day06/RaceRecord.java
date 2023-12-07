package de.co.ret.day06;

import java.util.List;
import java.util.stream.LongStream;

public record RaceRecord(int totalTimeMillis, long bestOfDistanceMillimeter) {
    public List<Long> calculateAllRaceOptions() {
        return getRaceOptions()
                .boxed().toList();
    }

    public List<Long> calculateWinningDistances() {
        return getRaceOptions()
                .filter(distanceOption -> distanceOption > bestOfDistanceMillimeter)
                .boxed().toList();
    }

    private LongStream getRaceOptions() {
        return LongStream.range(0, totalTimeMillis + 1)
                .map(time -> time * (totalTimeMillis - time));
    }
}
