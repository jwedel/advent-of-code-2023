package de.co.ret.day02.model;

import lombok.Getter;
import org.modelcc.Suffix;
import org.modelcc.Value;

@Suffix("blue")
@Getter
public class Blue extends ColorExpression {
    IntegerLiteral amount;
}
