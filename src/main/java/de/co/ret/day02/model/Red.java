package de.co.ret.day02.model;

import lombok.Getter;
import org.modelcc.Suffix;
import org.modelcc.Value;

@Suffix("red")
@Getter
public class Red extends ColorExpression{
    IntegerLiteral amount;
}
