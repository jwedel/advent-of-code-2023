package de.co.ret.day02;

import de.co.ret.day02.model.CubeConfigurationExpression;
import de.co.ret.day02.model.GameExpression;
import org.modelcc.io.ModelReader;
import org.modelcc.io.java.JavaLanguageReader;
import org.modelcc.language.metamodel.LanguageElement;
import org.modelcc.language.metamodel.LanguageModel;
import org.modelcc.metamodel.Model;
import org.modelcc.parser.Parser;
import org.modelcc.parser.ParserFactory;

import java.util.Arrays;
import java.util.List;

public record Game(int id, List<CubeConfiguration> revelations) {
    public static Game parse(String line) {
        try {
            Parser<GameExpression> parser = createParser();

            GameExpression gameExpression = parser.parse(line);

            return new Game(
                    gameExpression.getId().getValue(),
                    toCubeRevelations(gameExpression.getRevelations()));
        } catch (Exception e) {
            return null;
        }

    }

    private static Parser<GameExpression> createParser() throws Exception {
        ModelReader<LanguageModel> reader = new JavaLanguageReader(GameExpression.class);
        Model<LanguageElement> model = reader.read();
        Parser<GameExpression> parser = ParserFactory.create(model, ParserFactory.WHITESPACE);
        return parser;
    }

    private static List<CubeConfiguration> toCubeRevelations(List<CubeConfigurationExpression> revelationExpressions) {
        return revelationExpressions.stream()
                .map(CubeConfiguration::fromExpression)
                .toList();
    }

    private static int toGameId(String gameIdString) {
        return Integer.parseInt(gameIdString.split(" ")[1]);
    }

    public boolean isValidForConfiguration(int totalRedCount, int totalGreenCount, int totalBlueCount) {
        var minimumConfig = getMinimumConfiguration();

        return minimumConfig.red() <= totalRedCount && minimumConfig.green() <= totalGreenCount && minimumConfig.blue() <= totalBlueCount;
    }

    public CubeConfiguration getMinimumConfiguration() {
        int maxRedCount = 0;
        int maxGreenCount = 0;
        int maxBlueCount = 0;

        for (CubeConfiguration revelation : this.revelations) {
            maxRedCount = Math.max(revelation.red(), maxRedCount);
            maxGreenCount = Math.max(revelation.green(), maxGreenCount);
            maxBlueCount = Math.max(revelation.blue(), maxBlueCount);
        }

        return new CubeConfiguration(maxRedCount,maxGreenCount,maxBlueCount);
    }
}
