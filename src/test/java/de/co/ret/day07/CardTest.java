package de.co.ret.day07;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardTest {
    @Test
    void should_allow_comparing_cards() {
        assertThat(Card.ACE).isGreaterThan(Card.KING);
        assertThat(Card.KING).isGreaterThan(Card.QUEEN);
        assertThat(Card.FIVE).isGreaterThan(Card.TWO);
        assertThat(Card.TWO).isGreaterThan(Card.JOKER);
    }

    @Test
    void should_map_string_correctly() {
        assertThat(Card.parse("A")).isEqualTo(Card.ACE);
        assertThat(Card.parse("K")).isEqualTo(Card.KING);
        assertThat(Card.parse("Q")).isEqualTo(Card.QUEEN);
        assertThat(Card.parse("J")).isEqualTo(Card.JOKER);
        assertThat(Card.parse("T")).isEqualTo(Card.TEN);
        assertThat(Card.parse("9")).isEqualTo(Card.NINE);
        assertThat(Card.parse("8")).isEqualTo(Card.EIGHT);
        assertThat(Card.parse("7")).isEqualTo(Card.SEVEN);
        assertThat(Card.parse("6")).isEqualTo(Card.SIX);
        assertThat(Card.parse("5")).isEqualTo(Card.FIVE);
        assertThat(Card.parse("4")).isEqualTo(Card.FOUR);
        assertThat(Card.parse("3")).isEqualTo(Card.THREE);
        assertThat(Card.parse("2")).isEqualTo(Card.TWO);
    }
}