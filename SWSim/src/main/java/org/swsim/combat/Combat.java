package org.swsim.combat;

import org.swsim.action.Action;
import org.swsim.character.Character;
import org.swsim.core.Deck;

import java.util.Comparator;
import java.util.List;

public class Combat {
    public void gameLoop() {
        while (!isGameOver()) {
            for (Character c: characters)
                c.drawTurnOrder(deck);
            characters.sort(Comparator.comparing(Character::getTurnOrder));
            for (Character c: characters) {
                Action a = c.getPlayer().play(c, characters);
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
