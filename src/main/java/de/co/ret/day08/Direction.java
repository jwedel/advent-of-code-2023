package de.co.ret.day08;

public enum Direction {
    LEFT, RIGHT;

    public static Direction parse(String directionString) {
        return switch (directionString) {
            case "L" -> LEFT;
            case "R" -> RIGHT;
            default -> throw new IllegalArgumentException("Unknown direction: " + directionString);
        };
    }
}
