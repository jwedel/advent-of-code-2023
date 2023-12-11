package de.co.ret.day05;

import java.util.Optional;

public record AlmanacEntry(long destinationRangeStart, long sourceRangeStart, long rangeLength) {
    public static AlmanacEntry parse(String line) {
        var components = line.split(" ");

        if(components.length != 3) {
            throw new IllegalArgumentException("Unable to parse almanac entry: " + line);
        }

        return new AlmanacEntry(
                Long.parseLong(components[0]),
                Long.parseLong(components[1]),
                Long.parseLong(components[2])
        );
    }

    public Optional<Long> findMatch(long sourceId) {
        if(!inRange(sourceId)) {
            return Optional.empty();
        }
        return Optional.of(mapToTarget(sourceId));
    }

    public long getLastSourceIndex() {
        return sourceRangeStart + rangeLength - 1;
    }

    private long mapToTarget(long sourceId) {
        return destinationRangeStart + (sourceId - sourceRangeStart);
    }

    private boolean inRange(long sourceId) {
        return sourceId >= sourceRangeStart && sourceId < (sourceRangeStart + rangeLength);
    }
}
