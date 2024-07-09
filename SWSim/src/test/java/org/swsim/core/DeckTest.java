package org.swsim.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    @Test
    void shuffle() {
        Deck deck = new Deck(0);
        deck.shuffle();
        Card first = deck.draw();
        assertEquals(4, first.getNumber());
        assertEquals(Suit.SPADES, first.getSuit());
        deck.shuffle();
        Card second = deck.draw();
        assertEquals(5, second.getNumber());
        assertEquals(Suit.HEARTS, second.getSuit());
    }

    @Test
    void draw() {
        Deck deck = new Deck(0);
        assertEquals(54, deck.remainingCards());
        Card first = deck.draw();
        assertEquals(15, first.getNumber());
        assertEquals(53, deck.remainingCards());
    }
}