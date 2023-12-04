package de.co.ret.day02.model;

import lombok.Getter;
import org.modelcc.Prefix;
import org.modelcc.Separator;
import org.modelcc.Suffix;
import org.modelcc.Value;

import java.util.List;

@Prefix("Game")
@Getter
public class GameExpression extends Expression {
    @Suffix(":")
    @Value
    IntegerLiteral id;

    @Separator(";")
    List<CubeConfigurationExpression> revelations;
}
