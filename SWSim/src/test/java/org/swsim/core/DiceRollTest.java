package org.swsim.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiceRollTest {
    @Test
    void roll() {
        DiceRoll diceRoll = new DiceRoll(0, false);
        diceRoll.addDie(6).addDie(6);
        assertEquals(10, diceRoll.roll());
    }

    @Test
    void reset() {
        DiceRoll diceRoll = new DiceRoll(0, false);
        diceRoll.addDie(6);
        assertEquals(5, diceRoll.roll());
        diceRoll.reset();
        assertEquals(0, diceRoll.roll());
    }
}