package de.co.ret.day08;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

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
        ), (a -> true));

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
                        Map.of(
                                "AAA", AAA,
                                "BBB", BBB,
                                "CCC", CCC,
                                "DDD", DDD,
                                "EEE", EEE,
                                "GGG", GGG,
                                "ZZZ", ZZZ
                        ),
                        List.of())

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
                ),
                (node -> node.equals("AAA"))
        );

        var steps = mapDocument.stepsToWalkTo("ZZZ"::equals);
        assertThat(steps).isEqualTo(2L);
    }

    @Test
    void should_return_correct_number_of_steps_for_given_directions_including_retries() {
        MapDocument mapDocument = MapDocument.fromLines(List.of(
                        "LLR",
                        "",
                        "AAA = (BBB, BBB)",
                        "BBB = (AAA, ZZZ)",
                        "ZZZ = (ZZZ, ZZZ)"
                ),
                (node -> node.equals("AAA"))
        );

        var steps = mapDocument.stepsToWalkTo("ZZZ"::equals);
        assertThat(steps).isEqualTo(6L);
    }

    @Test
    void should_return_correct_number_of_steps_for_given_directions_including_retries_for_multiple_paths() {
        MapDocument mapDocument = MapDocument.fromLines(List.of(
                        "LR",
                        "",
                        "11A = (11B, XXX)",
                        "11B = (XXX, 11Z)",
                        "11Z = (11B, XXX)",
                        "22A = (22B, XXX)",
                        "22B = (22C, 22C)",
                        "22C = (22Z, 22Z)",
                        "22Z = (22B, 22B)",
                        "XXX = (XXX, XXX)"
                ),
                (node -> node.endsWith("A"))
        );

        var steps = mapDocument.stepsToWalkTo((node -> node.endsWith("Z")));
        assertThat(steps).isEqualTo(6L);
    }
}