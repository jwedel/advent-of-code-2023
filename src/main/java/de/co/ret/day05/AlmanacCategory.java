package de.co.ret.day05;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public record AlmanacCategory(String categoryName, List<AlmanacEntry> entries) {
    public AlmanacMatch getDirectMapping(long sourceId) {
        long destinationId = entries.stream()
                .map(entry -> entry.findMatch(sourceId))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .orElse(sourceId);
        return new AlmanacMatch(this.categoryName, sourceId, destinationId);
    }
}
