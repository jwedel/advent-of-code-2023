package de.co.ret.day07;

public enum Card {
    JOKER,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    QUEEN,
    KING,
    ACE;

    public static Card parse(String cardString) {
        return switch (cardString) {
            case "A" -> ACE;
            case "K" -> KING;
            case "Q" -> QUEEN;
            case "J" -> JOKER;
            case "T" -> TEN;
            case "9" -> NINE;
            case "8" -> EIGHT;
            case "7" -> SEVEN;
            case "6" -> SIX;
            case "5" -> FIVE;
            case "4" -> FOUR;
            case "3" -> THREE;
            case "2" -> TWO;
            default -> throw new IllegalArgumentException("Unknown card type: " + cardString);
        };
    }
}
