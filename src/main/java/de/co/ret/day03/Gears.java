package de.co.ret.day03;

public record Gears(int first, int second) {
    public int ratio() {
        return first * second;
    }
}
