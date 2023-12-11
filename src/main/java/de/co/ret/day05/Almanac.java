package de.co.ret.day05;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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

    public long findMinimumLocationId(Iterator<Long> indexIterator) {
        Iterable<Long> iterable = () -> this.chainWithAlmanacIterators(indexIterator);
        Stream<Long> targetStream = StreamSupport.stream(iterable.spliterator(), false);
        return targetStream.reduce(Long.MAX_VALUE, Long::min);
    }

    private Iterator<Long> chainWithAlmanacIterators(Iterator<Long> source) {
        var targetIterator = source;
        for (AlmanacCategory category : this.categories) {
            targetIterator = new AlmanacCategoryIterator(targetIterator, category);
        }
        return targetIterator;
    }
}
