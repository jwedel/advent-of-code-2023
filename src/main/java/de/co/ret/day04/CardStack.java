package de.co.ret.day04;

import java.util.List;
import java.util.stream.Collectors;

public class CardStack {
    private final List<Card> cards;

    public CardStack(List<Card> cards) {

        this.cards = cards;
    }

    public static CardStack fromLines(List<String> lines) {
        return new CardStack(lines.stream()
                .map(Card::parse)
                .toList());
    }

    public int calculateTotalPoints() {
        return this.cards.stream()
                .map(Card::calculatePoints)
                .reduce(0, Integer::sum);
    }
}
