package de.co.ret.day04;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Card(int cardId, List<Integer> winningNumbers, List<Integer> myNumbers) {

    public static final Pattern CARD_PATTERN = Pattern.compile("Card\\s+(?<cardId>\\d+):(?<winningNumbers>[^|]+)\\|(?<myNumbers>[^|]+)");

    public static Card parse(String cardString) {
        Matcher cardMatcher = CARD_PATTERN.matcher(cardString);
        if (!cardMatcher.matches()) {
            throw new IllegalArgumentException("Unable to parse card");
        }

        int cardId = Integer.parseInt(cardMatcher.group("cardId"));
        List<Integer> winningNumbers = toNumberList(cardMatcher.group("winningNumbers"));
        List<Integer> myNumbers = toNumberList(cardMatcher.group("myNumbers"));

        return new Card(cardId, winningNumbers, myNumbers);
    }

    private static List<Integer> toNumberList(String numbersString) {
        return Arrays.stream(numbersString.trim().split("\\s+"))
                .map(Integer::parseInt).toList();
    }

    public int calculatePoints() {
        long matchingNumbers = getMatchingNumbers();
        if (matchingNumbers == 0) {
            return 0;
        }

        return (int) Math.pow(2, matchingNumbers - 1);
    }

    public int getMatchingNumbers() {
        return (int)myNumbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }
}
