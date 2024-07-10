package org.swsim.action;

import org.junit.jupiter.api.Test;
import org.swsim.character.Character;

import static org.junit.jupiter.api.Assertions.*;

class AttackTest {

    @Test
    public void simpleAttack() {
        Character attacker = new Character();
        Character target = new Character();
        attacker.setAttribute("Str", "d6");
        Attack attack = new Attack("Str", "Fighting");
        attack.setAttacker(attacker);
        attack.setTarget(target);
        attack.execute();
    }
}