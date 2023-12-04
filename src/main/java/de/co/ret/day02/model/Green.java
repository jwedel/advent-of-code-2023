package de.co.ret.day02.model;

import lombok.Getter;
import org.modelcc.Suffix;
import org.modelcc.Value;

@Suffix("green")
@Getter
public class Green extends ColorExpression{
    IntegerLiteral amount;
}
