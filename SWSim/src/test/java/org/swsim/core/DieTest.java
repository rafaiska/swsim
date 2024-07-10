package org.swsim.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DieTest {
    @Test
    void roll() {
        Die die = new Die(6);
        Assertions.assertEquals(5, die.roll());
    }

    @Test
    void rollWithAces() {
        Die normalDie = new Die(6);
        Assertions.assertEquals(5, normalDie.rollWithAces());

        Die dieWithAce = new Die(6, 5);
        Assertions.assertEquals(9, dieWithAce.rollWithAces());
        Assertions.assertEquals(2, dieWithAce.aceRollValues.size());
        Assertions.assertEquals(6, dieWithAce.aceRollValues.get(0));
        Assertions.assertEquals(3, dieWithAce.aceRollValues.get(1));
    }
}