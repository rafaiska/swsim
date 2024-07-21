package org.swsim.core;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiceRollTest {
    @BeforeAll
    static void configureSeed() {
        RandomSeedManager.getInstance().setSeed(6);
    }

    @Test
    void roll() {
        DiceRoll diceRoll = new DiceRoll();
        diceRoll.addDie(6).addDie(6).addMod(1);
        assertEquals(7, diceRoll.roll());
    }

    @Test
    void reset() {
        DiceRoll diceRoll = new DiceRoll();
        diceRoll.addDie(6);
        assertEquals(3, diceRoll.roll());
        diceRoll.reset();
        assertEquals(0, diceRoll.roll());
    }
}