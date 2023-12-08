package de.co.ret.day07;

import java.util.List;

public class CamelCards {
    public static List<HandBid> fromLines(List<String> lines) {
        return lines.stream()
                .map(HandBid::parse)
                .toList();
    }
}
