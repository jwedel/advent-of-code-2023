package de.co.ret.day02;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameTest {
    @Test
    void should_return_parsed_game() {
        var game = Game.parse("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green");

        assertThat(game.id()).isEqualTo(1);
        assertThat(game.revelations().size()).isEqualTo(3);

        assertThat(game.revelations().get(0).red()).isEqualTo(4);
        assertThat(game.revelations().get(0).green()).isEqualTo(0);
        assertThat(game.revelations().get(0).blue()).isEqualTo(3);

        assertThat(game.revelations().get(1).red()).isEqualTo(1);
        assertThat(game.revelations().get(1).green()).isEqualTo(2);
        assertThat(game.revelations().get(1).blue()).isEqualTo(6);

        assertThat(game.revelations().get(2).red()).isEqualTo(0);
        assertThat(game.revelations().get(2).green()).isEqualTo(2);
        assertThat(game.revelations().get(2).blue()).isEqualTo(0);
    }

    @Test
    void should_return_true_if_valid_configuration() {
        var game = Game.parse("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green");

        assertThat(game.isValidForConfiguration(12,13,14)).isTrue();
    }

    @Test
    void should_return_false_if_invalid_configuration() {
        var game = Game.parse("Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red");

        assertThat(game.isValidForConfiguration(12,13,14)).isFalse();
    }

    @Test
    void shoud_return_other_minimum_configuration() {
        var game = Game.parse("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green");

        CubeConfiguration minimumConfiguration = game.getMinimumConfiguration();

        assertThat(minimumConfiguration.red()).isEqualTo(4);
        assertThat(minimumConfiguration.green()).isEqualTo(2);
        assertThat(minimumConfiguration.blue()).isEqualTo(6);
    }

    @Test
    void shoud_return_minimum_configuration() {
        var game = Game.parse("Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red");

        CubeConfiguration minimumConfiguration = game.getMinimumConfiguration();

        assertThat(minimumConfiguration.red()).isEqualTo(20);
        assertThat(minimumConfiguration.green()).isEqualTo(13);
        assertThat(minimumConfiguration.blue()).isEqualTo(6);
    }
}