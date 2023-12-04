package de.co.ret.day02.model;

import lombok.Getter;
import org.modelcc.FreeOrder;
import org.modelcc.Separator;

import java.util.List;

@FreeOrder
@Getter
public class CubeConfigurationExpression extends Expression {

    @Separator(",")
    List<ColorExpression> colors;
}
