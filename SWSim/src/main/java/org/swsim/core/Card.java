package org.swsim.core;

public class Card implements Comparable<Card>{
    public Card(int number, Suit suit) {
        this.number = number;
        this.suit = suit;
        check();
    }

    public static Card createJoker() {
        return new Card(15, null);
    }

    @Override
    public String toString() {
        return String.format("%s of %s", getNumberOrName(), suit.toString());
    }



    private String getNumberOrName() {
        switch (number) {
            case (14): return "A";
            case (11): return "J";
            case (12): return "Q";
            case (13): return "K";
            case (15): return "Joker";
            default: return String.valueOf(number);
        }
    }

    private final int number;
    private final Suit suit;

    @Override
    public int compareTo(Card o) {
        if (this.number == o.number) {
            return Integer.compare(this.suit.getOrder(), o.suit.getOrder());
        }
        else {
            return Integer.compare(this.number, o.number);
        }
    }

    private void check() {
        if (number < 2 || number > 15 || (number != 15 && suit == null))
            throw new WrongCardFormat();
    }

    private static class WrongCardFormat extends RuntimeException {}
}
