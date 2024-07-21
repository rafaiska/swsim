package org.swsim.action;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.swsim.attribute.Attribute;
import org.swsim.character.Character;
import org.swsim.core.RandomSeedManager;

import static org.junit.jupiter.api.Assertions.*;

class AttackTest {
    @BeforeAll
    public static void configureSeed() {
        RandomSeedManager.getInstance().setSeed(2);
    }

    @Test
    public void simpleAttack() {
        Character target = new Character();
        target.setAttribute("Parry", new Attribute("4"));

        Character attacker = new Character();
        attacker.setAttribute("Str", new Attribute("da6"));
        attacker.setAttribute("Fighting", new Attribute("da8"));

        Attack attack = new Attack(new Attribute("Fighting"), new Attribute("Str"));
        attack.setMelee(true);
        attack.setActor(attacker);
        attack.setTarget(target);
        attack.execute();

        assertTrue(attack.wasRaised());
        assertEquals(9, attack.getAttackResult());
        assertEquals(11, attack.getDamageResult());
    }
}