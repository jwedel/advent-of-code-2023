package de.co.ret.day07;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HandTypeTest {
    @Test
    void should_compare_types() {
        assertThat(HandType.FIVE_OF_A_KIND).isGreaterThan(HandType.FOUR_OF_A_KIND);
        assertThat(HandType.FOUR_OF_A_KIND).isGreaterThan(HandType.FULL_HOUSE);
        assertThat(HandType.FULL_HOUSE).isGreaterThan(HandType.THREE_OF_A_KIND);
        assertThat(HandType.THREE_OF_A_KIND).isGreaterThan(HandType.TWO_PAIR);
        assertThat(HandType.TWO_PAIR).isGreaterThan(HandType.ONE_PAIR);
        assertThat(HandType.ONE_PAIR).isGreaterThan(HandType.HIGH_CARD);
    }
}