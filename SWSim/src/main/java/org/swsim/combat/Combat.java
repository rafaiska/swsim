package org.swsim.combat;

import org.swsim.action.Action;
import org.swsim.character.Character;
import org.swsim.core.Deck;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Combat {
    public void gameLoop() {
        while (!isGameOver()) {
            for (Character c: characters)
                c.drawTurnOrder(deck);
            characters.sort(Comparator.comparing(Character::getTurnOrder));
            List<Action> turnActions = new ArrayList<>();
            for (Character c: characters) {
                turnActions.add(c.getPlayer().play(c, characters));
            }
            for (Character c: characters) {
                Action reaction = c.getPlayer().react(c, turnActions);
                if (reaction != null)
                    turnActions.add(reaction);
            }
            for (Action a: turnActions) {
                a.execute();
            }
        }
    }

    private boolean isGameOver() {
        return true;
    }

    private List<Character> characters;
    private Deck deck;
}
