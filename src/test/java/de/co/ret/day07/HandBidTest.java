package de.co.ret.day07;

import org.junit.jupiter.api.Test;

import java.util.List;

import static de.co.ret.day07.Card.*;
import static org.assertj.core.api.Assertions.assertThat;

class HandBidTest {
    @Test
    void should_parse_hand_bid() {
        HandBid hand = HandBid.parse("AATKJ 840");

        assertThat(hand).isEqualTo(new HandBid(new Hand(
                List.of(ACE, ACE, TEN, KING, JOKER)),
                840
        ));
    }
}