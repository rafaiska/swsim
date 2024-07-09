package org.swsim.player;

import org.swsim.action.Action;
import org.swsim.action.Attack;
import org.swsim.action.SkipTurn;
import org.swsim.character.Character;

import java.util.List;

public class DummyPlayer extends Player {
    @Override
    public Action play(Character character, List<Character> charactersInCombat) {
        List<Action> actions = character.getAvailableActions();
        Attack attack = null;
        for (Action a: actions) {
            if (a instanceof Attack) {
                attack = (Attack) a;
                break;
            }
        }

        if (attack == null) {
            return new SkipTurn();
        }

        Character attackable = findFirstAttackable(character, charactersInCombat);

        if (attackable == null)
            return new SkipTurn();

        attack.setAttacker(character);
        attack.setTarget(attackable);
        return attack;
    }

    private Character findFirstAttackable(Character character, List<Character> charactersInCombat) {
        for (Character c: charactersInCombat) {
            if (c != character && !c.isAlly(character))
                return c;
        }
        return null;
    }
}
