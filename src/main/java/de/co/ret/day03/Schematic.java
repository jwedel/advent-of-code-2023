package de.co.ret.day03;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static de.co.ret.day03.Schematic.ParserState.NUMBER;
import static de.co.ret.day03.Schematic.ParserState.OTHER;

public class Schematic {
    private final List<String> lines;

    public Schematic(List<String> lines) {
        this.lines = lines;
    }

    public static Schematic fromLines(List<String> lines) {
        return new Schematic(lines);
    }

    public List<Integer> getPartIds() {
        var partIds = new ArrayList<Integer>();

        var state = OTHER;
        var isValidPartId = false;
        var numberString = new StringBuilder();
        ;
        for (int y = 0; y < getMaxY(); y++) {
            for (int x = 0; x < getMaxX(); x++) {
                Character currentPoint = getPoint(x, y).orElse('.');
                switch (state) {
                    case NUMBER -> {
                        if (Character.isDigit(currentPoint)) {
                            numberString.append(currentPoint);

                            if(hasAdjacentSymbol(x, y)) {
                                isValidPartId = true;
                            }

                        } else {
                            if (isValidPartId) {
                                partIds.add(Integer.parseInt(numberString.toString()));
                            }
                            state = OTHER;
                            isValidPartId = false;
                        }
                    }
                    case OTHER -> {
                        if (Character.isDigit(currentPoint)) {
                            numberString = new StringBuilder();
                            numberString.append(currentPoint);
                            if(hasAdjacentSymbol(x, y)) {
                                isValidPartId = true;
                            }
                            state = NUMBER;
                        }

                    }
                }
            }
        }

        return partIds;
    }

    private boolean hasAdjacentSymbol(int x, int y) {
        return isSymbol(getPoint(x - 1, y))
                || isSymbol(getPoint(x - 1, y - 1))
                || isSymbol(getPoint(x, y - 1))
                || isSymbol(getPoint(x + 1, y - 1))
                || isSymbol(getPoint(x + 1, y))
                || isSymbol(getPoint(x + 1, y + 1))
                || isSymbol(getPoint(x, y + 1))
                || isSymbol(getPoint(x - 1, y + 1));
    }

    private boolean isSymbol(Optional<Character> optionalPoint) {
        char point = optionalPoint.orElse('.');
        return !Character.isDigit(point) && point != '.';
    }

    private Optional<Character> getPoint(int x, int y) {
        return isInSchematic(x, y) ? Optional.of(lines.get(y).charAt(x)) : Optional.empty();
    }

    private boolean isInSchematic(int x, int y) {
        return y >= 0 && y < lines.size() &&
                x >= 0 && x < getMaxX();
    }

    private int getMaxX() {
        return !lines.isEmpty() ? lines.getFirst().length() : 0;
    }

    private int getMaxY() {
        return lines.size();
    }

    enum ParserState {
        OTHER,
        NUMBER
    }
}
