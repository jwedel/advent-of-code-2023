package de.co.ret.day01;

import de.co.ret.common.Sum;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Calibration {

    public List<Integer> getValues() {
        return values;
    }

    private final List<Integer> values;

    private static final Map<String, String> digits = Map.of(
            "one","1",
            "two","2",
            "three","3",
            "four","4",
            "five","5",
            "six","6",
            "seven","7",
            "eight","8",
            "nine", "9"
    );

    private Calibration(List<String> lines) throws IOException {
        this.values = lines
                .stream()
                .map(Calibration::cleanValue)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static String cleanValue(String value) {
        var numbers = replaceNumberStrings(value).chars()
                .filter(Calibration::isNumber)
                .boxed()
                .map(Character::toString)
                .toList();
        return "%s%s".formatted(numbers.getFirst(), numbers.getLast());
    }

    private static String replaceNumberStrings(String value) {
        var result = new StringBuilder();
        for (int i = 0; i < value.length();i++) {

            final int offset = i;

            var digitMatch = digits.entrySet().stream()
                    .filter(entry -> value.startsWith(entry.getKey(), offset))
                    .findFirst();
            if(digitMatch.isPresent()) {
                Map.Entry<String, String> digitEntry = digitMatch.get();
                result.append(digitEntry.getValue());
            } else{
                result.append(value.charAt(i));
            }
        }
        return result.toString();
    }

    private static boolean isNumber(int codePoint) {
        return Character.isDigit(codePoint);
    }

    public static Calibration fromLines(List<String> lines) throws URISyntaxException, IOException {
        return new Calibration(lines);
    }
}
