package de.co.ret.day07;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
@ToString(exclude = {"type", "hasJoker", "handWithJokers"})
@EqualsAndHashCode(exclude = {"type", "hasJoker", "handWithJokers"})
public final class Hand implements Comparable<Hand> {
    private final List<Card> cards;
    private final HandType type;
    private final boolean hasJoker;
    private final Hand handWithJokers;

    public Hand(List<Card> cards) {
        this.cards = cards;
        this.hasJoker = cards.stream().anyMatch(Card.JOKER::equals);
        this.handWithJokers = calculateWithJokers();
        this.type = calculateType();
    }

    public static Hand parse(String handString) {
        return new Hand(Arrays.stream(handString.split("")).map(Card::parse).toList());
    }

    private HandType calculateType() {
        var distinctCards = groupByType();

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

    private Map<Card, List<Card>> groupByType() {
        return cards.stream().collect(Collectors.groupingBy(Function.identity()));
    }


    @Override
    public int compareTo(Hand other) {
        if (this.equals(other)) {
            return 0;
        }

        int comparisonByType = this.withJokers().getType().compareTo(other.withJokers().getType());

        if (comparisonByType != 0) {
            return comparisonByType;
        }

        return IntStream.range(0, 5)
                .map(i -> this.cards.get(i).compareTo(other.cards.get(i)))
                .filter(cardComparison -> cardComparison != 0)
                .findFirst()
                .orElse(0);
    }

    public Hand withJokers() {
        return this.handWithJokers;
    }

    private Hand calculateWithJokers() {
        if (!hasJoker) {
            return this;
        }

        var distinctCards = groupByType();
        Card maxGroupCard = distinctCards.entrySet().stream()
                .filter(entry -> !entry.getKey().equals(Card.JOKER))
                .max(Comparator.comparingInt(a -> a.getValue().size()))
                .map(Map.Entry::getKey)
                .orElse(Card.ACE);

        return new Hand(this.cards.stream()
                .map(card -> card.equals(Card.JOKER) ? maxGroupCard : card)
                .toList());
    }
}
