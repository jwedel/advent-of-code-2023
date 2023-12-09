package de.co.ret.day08;

import org.junit.jupiter.api.Test;

import java.util.List;

import static de.co.ret.day08.Direction.LEFT;
import static de.co.ret.day08.Direction.RIGHT;
import static org.assertj.core.api.Assertions.assertThat;

class MapDocumentTest {
    @Test
    void should_parse_document() {
        MapDocument mapDocument = MapDocument.fromLines(List.of(
                "RLRRL",
                "",
                "AAA = (BBB, CCC)",
                "BBB = (DDD, EEE)",
                "CCC = (ZZZ, GGG)",
                "DDD = (DDD, DDD)",
                "EEE = (EEE, EEE)",
                "GGG = (GGG, GGG)",
                "ZZZ = (ZZZ, ZZZ)"
        ));

        var ZZZ = Node.of(null, null, "ZZZ");
        var GGG = Node.of(null, null, "GGG");
        GGG.setLeft(GGG);
        GGG.setRight(GGG);
        var EEE = Node.of(null, null, "EEE");
        EEE.setLeft(EEE);
        EEE.setRight(EEE);
        var DDD = Node.of(null, null, "DDD");
        DDD.setLeft(DDD);
        DDD.setRight(DDD);
        var CCC = Node.of(ZZZ, GGG, "CCC");
        var BBB = Node.of(DDD, EEE, "BBB");
        var AAA = Node.of(BBB, CCC, "AAA");

        assertThat(mapDocument).isEqualTo(
                new MapDocument(
                        List.of(RIGHT, LEFT, RIGHT, RIGHT, LEFT),
                        AAA)
        );
    }

    @Test
    void should_return_correct_number_of_steps_for_given_directions() {
        MapDocument mapDocument = MapDocument.fromLines(List.of(
                "RL",
                "",
                "AAA = (BBB, CCC)",
                "BBB = (DDD, EEE)",
                "CCC = (ZZZ, GGG)",
                "DDD = (DDD, DDD)",
                "EEE = (EEE, EEE)",
                "GGG = (GGG, GGG)",
                "ZZZ = (ZZZ, ZZZ)"
        ));

        assertThat(mapDocument.stepsToWalkTo("ZZZ")).isEqualTo(2);
    }

    @Test
    void should_return_correct_number_of_steps_for_given_directions_including_retries() {
        MapDocument mapDocument = MapDocument.fromLines(List.of(
                "LLR",
                "",
                "AAA = (BBB, BBB)",
                "BBB = (AAA, ZZZ)",
                "ZZZ = (ZZZ, ZZZ)"
        ));

        assertThat(mapDocument.stepsToWalkTo("ZZZ")).isEqualTo(6);
    }
}