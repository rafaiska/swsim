package org.swsim.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    public Deck(int seed) {
        generator = new Random(seed);
        for (int n = 2; n<= 14; n++)
            for (Suit s: Suit.values())
                cards.add(new Card(n, s));
        cards.add(Card.createJoker());
        cards.add(Card.createJoker());
    }

    public void shuffle() {
        for (int i= cards.size() - 1; i >= 0; --i) {
            int chosenIndex = generator.nextInt() % (cards.size() - i);
            Card c = cards.remove(chosenIndex);
            cards.add(c);
        }
    }

    public void reset() {
        cards.addAll(discardPile);
        discardPile.clear();
        shuffle();
    }

    private final List<Card> cards = new ArrayList<>();
    private final List<Card> discardPile = new ArrayList<>();
    private final Random generator;

    public Card draw() {
        Card c = cards.remove(cards.size() - 1);
        discardPile.add(c);
        return c;
    }
}
