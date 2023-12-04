package de.co.ret.day02;

import de.co.ret.day02.model.GameExpression;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.modelcc.io.ModelReader;
import org.modelcc.io.java.JavaLanguageReader;
import org.modelcc.language.metamodel.LanguageElement;
import org.modelcc.language.metamodel.LanguageModel;
import org.modelcc.metamodel.Model;
import org.modelcc.parser.Parser;
import org.modelcc.parser.ParserFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GamesTest {
    @Test
    void should_return_valid_game_ids() {
        var lines = List.of(
                "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
                "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
                "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
                "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
                "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"
        );
        var games = GamesCollection.fromLines(lines);

        assertThat(games.getValidGameIds(12, 13, 14)).containsOnly(
                1,
                2,
                5
        );
    }
}