package de.co.ret.day07;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record Hand(List<Card> cards) implements Comparable<Hand> {
    public static Hand parse(String handString) {
        return new Hand(Arrays.stream(handString.split("")).map(Card::parse).toList());
    }

    public HandType getType() {
        var distinctCards = cards.stream().collect(Collectors.groupingBy(Function.identity()));

        return switch (distinctCards.size()) {
            case 5 -> HandType.HIGH_CARD;
            case 4 -> HandType.ONE_PAIR;
            case 3 -> {
                var maxSetSize = distinctCards.values().stream()
                        .map(List::size)
                        .max(Comparator.comparingInt(Integer::intValue))
                        .orElse(0);
                yield maxSetSize == 3 ? HandType.THREE_OF_A_KIND : HandType.TWO_PAIR;
            }
            case 2 -> {
                var firstSet = distinctCards.get(cards.get(0));
                yield firstSet.size() == 2 || firstSet.size() == 3 ?
                        HandType.FULL_HOUSE : HandType.FOUR_OF_A_KIND;
            }
            case 1 -> HandType.FIVE_OF_A_KIND;
            default -> throw new IllegalArgumentException("Unknown hand type: " + cards);
        };
    }


    @Override
    public int compareTo(Hand other) {
        if (this.equals(other)) {
            return 0;
        }

        int comparisonByType = this.getType().compareTo(other.getType());

        if (comparisonByType != 0) {
            return comparisonByType;
        }

        return IntStream.range(0, 5)
                .map(i -> this.cards.get(i).compareTo(other.cards.get(i)))
                .filter(cardComparison -> cardComparison != 0)
                .findFirst()
                .orElse(0);
    }
}
