package de.co.ret.day05;

public record SeedRange(long startId, long rangeLength) {
    public static SeedRange fromInterval(Long firstId, Long lastId) {
        return new SeedRange(firstId, (lastId - firstId) + 1);
    }
}
