package de.co.ret.day02.model;

import lombok.Getter;
import org.modelcc.Value;

@Getter
public class IntegerLiteral extends Expression{
    @Value
    int value;
}
