package de.co.ret.day06;

import java.util.List;
import java.util.stream.IntStream;

public record RaceRecord(int totalTimeMillis, int bestOfDistanceMillimeter) {
    public List<Integer> calculateAllRaceOptions() {
        return getRaceOptions()
                .boxed().toList();
    }

    public List<Integer> calculateWinningDistances() {
        return getRaceOptions()
                .filter(distanceOption -> distanceOption > bestOfDistanceMillimeter)
                .boxed().toList();
    }

    private IntStream getRaceOptions() {
        return IntStream.range(0, totalTimeMillis + 1)
                .map(time -> time * (totalTimeMillis - time));
    }
}
