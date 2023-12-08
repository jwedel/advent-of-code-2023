package de.co.ret.day07;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static de.co.ret.day07.Card.*;
import static org.assertj.core.api.Assertions.assertThat;

class HandTest {

    @Nested
    class Parser {
        @Test
        void should_parse_Hand() {
            Hand hand = Hand.parse("AATKJ");

            assertThat(hand).isEqualTo(new Hand(
                    List.of(ACE, ACE, TEN, KING, JOKER)
            ));
        }
    }

    @Nested
    class Type {

        @ParameterizedTest
        @ValueSource(strings = {
                "AAAAA",
                "KKKKK",
                "88888",
                "22222"
        })
        void should_rate_hand_as_Five_of_a_kind(String handString) {
            assertThat(Hand.parse(handString).getType()).isEqualTo(HandType.FIVE_OF_A_KIND);
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "AA8AA",
                "K3KKK",
                "22223",
                "32222"
        })
        void should_rate_hand_as_Four_of_a_kind(String handString) {
            assertThat(Hand.parse(handString).getType()).isEqualTo(HandType.FOUR_OF_A_KIND);
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "23332",
                "33222",
                "KJKJK",
                "TTT99",
        })
        void should_rate_hand_as_Full_House(String handString) {
            assertThat(Hand.parse(handString).getType()).isEqualTo(HandType.FULL_HOUSE);
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "TTT98",
                "34222",
                "KJKQK",
                "TTT98",
        })
        void should_rate_hand_as_Three_of_a_kind(String handString) {
            assertThat(Hand.parse(handString).getType()).isEqualTo(HandType.THREE_OF_A_KIND);
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "23432",
                "34322",
                "KJQQK"
        })
        void should_rate_hand_as_Two_pair(String handString) {
            assertThat(Hand.parse(handString).getType()).isEqualTo(HandType.TWO_PAIR);
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "A23A4",
                "23455",
                "KTJAK"
        })
        void should_rate_hand_as_One_pair(String handString) {
            assertThat(Hand.parse(handString).getType()).isEqualTo(HandType.ONE_PAIR);
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "23456",
                "AKQJT",
                "25TA4"
        })
        void should_rate_hand_as_High_card(String handString) {
            assertThat(Hand.parse(handString).getType()).isEqualTo(HandType.HIGH_CARD);
        }
    }

    @Nested
    class Comparison {

        @Test
        void should_compare_cards() {
            assertThat(Hand.parse("AAAAA").compareTo(Hand.parse("AAAAA"))).isEqualTo(0);
            assertThat(Hand.parse("AAAAA")).isGreaterThan(Hand.parse("KKKKK"));
            assertThat(Hand.parse("77777")).isLessThan(Hand.parse("KKKKK"));

            assertThat(Hand.parse("KTJJT")).isGreaterThan(Hand.parse("KK677"));
            assertThat(Hand.parse("QQQJA")).isGreaterThan(Hand.parse("T55J5"));

            assertThat(Hand.parse("KT972")).isGreaterThan(Hand.parse("27A83"));

        }
    }

    @Nested
    class UseJokers {
        @Test
        void should_leave_unchanged_when_no_jokers() {
            Hand hand = Hand.parse("32T3K").withJokers();

            assertThat(hand).isEqualTo(Hand.parse("32T3K"));
        }

        @Test
        void should_replace_jokers_to_get_Four_of_a_kind() {
            Hand hand = Hand.parse("T55J5").withJokers();

            assertThat(hand).isEqualTo(Hand.parse("T5555"));
        }

        @Test
        void should_replace_jokers_to_get_Four_of_a_kind_2() {
            Hand hand = Hand.parse("KTJJT").withJokers();

            assertThat(hand).isEqualTo(Hand.parse("KTTTT"));
        }

        @Test
        void should_replace_jokers_to_get_Four_of_a_kind_3() {
            Hand hand = Hand.parse("QQQJA").withJokers();

            assertThat(hand).isEqualTo(Hand.parse("QQQQA"));
        }

        @Test
        void should_replace_only_jokers_to_get_Five_of_a_kind() {
            Hand hand = Hand.parse("JJJJJ").withJokers();

            assertThat(hand).isEqualTo(Hand.parse("AAAAA"));
        }
    }
}