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
        assertEquals("+(3 (d6) +3 (d6) +1)", diceRoll.toString());
    }

    @Test
    void reset() {
        DiceRoll diceRoll = new DiceRoll();
        diceRoll.addDie(6);
        assertEquals(3, diceRoll.roll());
        assertEquals("+(3 (d6))", diceRoll.toString());

        diceRoll.reset();
        assertEquals(0, diceRoll.roll());
        assertEquals("+()", diceRoll.toString());
    }
}