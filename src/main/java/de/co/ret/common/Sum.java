package de.co.ret.common;

import java.util.List;

public class Sum {
    public static int ofInts(List<Integer> ints) {
        return ints.stream().reduce(Integer::sum).orElse(0);
    }
}
