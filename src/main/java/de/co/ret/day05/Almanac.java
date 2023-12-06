package de.co.ret.day05;

import java.util.ArrayList;
import java.util.List;

public record Almanac(List<AlmanacCategory> categories) {
    public List<AlmanacMatch> getDirectMapping(long sourceId) {
        var matchTrace = new ArrayList<AlmanacMatch>();
        long currentSourceId = sourceId;
        for (AlmanacCategory category : categories) {
            var match = category.getDirectMapping(currentSourceId);
            matchTrace.add(match);
            currentSourceId = match.destinationId();
        }
        return matchTrace;
    }

    public long getLastDestinationId(long sourceId) {
        long destinationId = sourceId;

        for (AlmanacCategory category : categories) {
            destinationId = category.getDestinationId(destinationId);
        }

        return destinationId;
    }
}
