package de.co.ret.day05;

import lombok.RequiredArgsConstructor;

import java.util.Iterator;

@RequiredArgsConstructor
public class AlmanacCategoryIterator implements Iterator<Long> {
    private final Iterator<Long> source;
    private final AlmanacCategory almanacCategory;
    private int currentEntryIndex = 0;

    @Override
    public boolean hasNext() {
        return source.hasNext();
    }

    @Override
    public Long next() {
        var sourceIndex = source.next();

        return mapToDestinationIndex(sourceIndex);
    }

    private Long mapToDestinationIndex(Long sourceIndex) {
        while (true) {
            if (currentEntryIndex >= almanacCategory.entries().size()) {
                return sourceIndex;
            }
            var entry = almanacCategory.entries().get(currentEntryIndex);
            if (sourceIndex < entry.sourceRangeStart()) {
                return sourceIndex;
            }
            if (sourceIndex >= entry.getLastSourceIndex()) {
                currentEntryIndex++;
            }

            if (sourceIndex <= entry.getLastSourceIndex()) {
                return entry.destinationRangeStart() + sourceIndex - entry.sourceRangeStart();
            }
        }
    }
}
