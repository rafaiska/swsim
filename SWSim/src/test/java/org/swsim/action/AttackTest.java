package org.swsim.action;

import org.junit.jupiter.api.Test;
import org.swsim.attribute.Attribute;
import org.swsim.character.Character;

import static org.junit.jupiter.api.Assertions.*;

class AttackTest {

    @Test
    public void simpleAttack() {
        Character target = new Character();
        target.setAttribute("Parry", new Attribute("4"));

        Character attacker = new Character();
        attacker.setAttribute("Str", new Attribute("d6"));
        attacker.setAttribute("Fighting", new Attribute("d8"));

        Attack attack = new Attack(new Attribute("Fighting"), new Attribute("Str"));
        attack.setActor(attacker);
        attack.setTarget(target);
        attack.execute();

        assertTrue(attack.wasRaised());
        assertEquals(8, attack.getAttackResult());
        assertEquals(4, attack.getDamageResult());
    }
}