package de.co.ret.day07;

public record HandBid(Hand hand, int bid) {
    public static HandBid parse(String handBidString) {
        String[] tokens = handBidString.split((" "));
        var cards = tokens[0].trim();
        var bid = Integer.parseInt(tokens[1].trim());
        return new HandBid(Hand.parse(cards), bid);
    }
}
