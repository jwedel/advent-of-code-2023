package de.co.ret.day09;

import java.util.Arrays;
import java.util.List;

public record OASISMeasurements(List<Long> measurements) {
    public static OASISMeasurements parseLine(String measurementsString) {
        String[] tokens = measurementsString.split("\\s+");

        return new OASISMeasurements(Arrays.stream(tokens)
                .map(String::trim)
                .map(Long::parseLong)
                .toList());
    }

    public long extrapolateNextValue() {
        var rightValue = getDiffValue(1, 0);
        return extrapolate(2, rightValue, 0);
    }

    private long extrapolate(int leftIndex, long rightValue, int level) {
        long secondToLast = getDiffValue(leftIndex, level);

        var diff = rightValue - secondToLast;

        if (rightValue == 0L && secondToLast == 0L) {
            return 0L;
        }
        return rightValue + extrapolate(leftIndex, diff, level + 1);
    }

    private long getDiffValue(int index, int level) {
        if (level == 0) {
            return measurements.get(measurements().size() - index);
        }

        return getDiffValue(index, level - 1) - getDiffValue(index + 1, level - 1);
    }
}
