package org.swsim.player;

import org.swsim.action.Action;
import org.swsim.action.Attack;
import org.swsim.action.SkipTurn;
import org.swsim.action.TakeDamage;
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

        attack.setTarget(character);
        attack.setTarget(attackable);
        return attack;
    }

    @Override
    public Action react(Character c, List<Action> turnActions) {
        for (Action action : turnActions) {
            Action reaction = reactTo(action);
            if (reaction != null)
                return reaction;
        }
        return null;
    }

    private Action reactTo(Action action) {
        return null;
    }

    private Action reactTo(Attack attack) {
        return new TakeDamage(attack);
    }

    private Character findFirstAttackable(Character character, List<Character> charactersInCombat) {
        for (Character c: charactersInCombat) {
            if (c != character && !c.isAlly(character))
                return c;
        }
        return null;
    }
}
