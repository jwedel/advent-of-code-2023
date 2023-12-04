package de.co.ret.day04;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public int calculateScratchCardInstances() {
        int[] cardInstances = new int[cards.size()];
        Arrays.fill(cardInstances, 1);

        for (int currentCard = 0; currentCard < cards.size(); currentCard++) {
            var currentCardInstances = cardInstances[currentCard];
            for(int instance = 0; instance < currentCardInstances; instance++) {
                Card card = cards.get(currentCard);
                int matchingNumbers = card.getMatchingNumbers();
                for (int matchingNumber = 0; matchingNumber < matchingNumbers; matchingNumber++) {
                    cardInstances[currentCard + matchingNumber + 1] += 1;
                }
            }
        }

        return Arrays.stream(cardInstances).reduce(0, Integer::sum);
    }
}
