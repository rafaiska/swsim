package org.swsim.core;

public enum Suit {
    HEARTS(1, "Hearts"), CLUBS(3, "Clubs"), DIAMONDS(2, "Diamonds"), SPADES(0, "Spades");

    Suit(int order, String name) {
        this.order = order;
        this.name = name;
    }

    private final int order;
    private final String name;

    public int getOrder() {
        return order;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
