package de.co.ret.day05;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public record AlmanacCategory(String categoryName, List<AlmanacEntry> entries) {
    public AlmanacCategory(String categoryName, List<AlmanacEntry> entries) {
        this.categoryName = categoryName;
        this.entries = entries.stream()
                .sorted(Comparator.comparingLong(AlmanacEntry::sourceRangeStart))
                .toList();
    }

    public AlmanacMatch getDirectMapping(long sourceId) {
        var destinationId = searchBinary(entries, sourceId, 0, entries.size() - 1);
        return new AlmanacMatch(this.categoryName, sourceId, destinationId);
    }

    public long getDestinationId(long sourceId) {
        return searchBinary(entries, sourceId, 0, entries.size() - 1);
    }

    public long searchBinary(
            List<AlmanacEntry> sortedList, long sourceId, int lowIndex, int highIndex) {
        int middle = lowIndex + ((highIndex - lowIndex) / 2);

        if (highIndex < lowIndex) {
            return sourceId;
        }

        AlmanacEntry middleEntry = sortedList.get(middle);
        Optional<Long> match = middleEntry.findMatch(sourceId);
        if (match.isPresent()) {
            return match.get();
        } else if (sourceId < middleEntry.sourceRangeStart()) {
            return searchBinary(
                    sortedList, sourceId, lowIndex, middle - 1);
        } else {
            return searchBinary(
                    sortedList, sourceId, middle + 1, highIndex);
        }
    }
}
