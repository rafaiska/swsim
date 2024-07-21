package org.swsim.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DieTest {
    @BeforeAll
    static void configureSeed() {
        RandomSeedManager.getInstance().setSeed(1);
    }

    @Test
    void roll() {
        Die die = new Die(6, false);
        Assertions.assertEquals(4, die.roll());
    }

    @Test
    void rollWithAces() {
        Die die = new Die(6, true);
        Assertions.assertEquals(4, die.rollWithAces());
        Assertions.assertEquals(3, die.rollWithAces());
        Assertions.assertEquals(4, die.rollWithAces());
        Assertions.assertEquals(1, die.rollWithAces());
        Assertions.assertEquals(5, die.rollWithAces());
        Assertions.assertEquals(3, die.rollWithAces());

        Assertions.assertEquals(8, die.rollWithAces());
        Assertions.assertEquals(2, die.aceRollValues.size());
        Assertions.assertEquals(6, die.aceRollValues.get(0));
        Assertions.assertEquals(2, die.aceRollValues.get(1));
    }
}