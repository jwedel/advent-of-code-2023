package de.co.ret.day08;

import java.util.List;

public class MapMath {
    public static long findLCM(List<Long> numbers) {
        long result = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            result = lcm(result, numbers.get(i));
        }
        return result;
    }

    private static long lcm(long a, long b) {
        return a * (b / hcf(a, b));
    }

    private static long hcf(long a, long b) {
        while (a != b) {
            if (a > b) {
                a -= b;
            } else {
                b -= a;
            }
        }
        return a;
    }
}
