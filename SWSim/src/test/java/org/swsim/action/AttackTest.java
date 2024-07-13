package org.swsim.action;

import org.junit.jupiter.api.Test;
import org.swsim.attribute.Attribute;
import org.swsim.character.Character;

import static org.junit.jupiter.api.Assertions.*;

class AttackTest {

    @Test
    public void simpleAttack() {
        Character attacker = new Character();
        Character target = new Character();
        attacker.setAttribute("Str", new Attribute("d6"));
        Attack attack = new Attack(new Attribute("Str"), new Attribute("Fighting"));
        attack.setTarget(attacker);
        attack.setTarget(target);
        attack.execute();
    }
}