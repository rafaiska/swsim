package org.swsim.character;

import org.swsim.action.Action;
import org.swsim.combat.Team;
import org.swsim.core.Card;
import org.swsim.core.Deck;
import org.swsim.player.Player;

import java.applet.Applet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Character {
    private HashMap<String, CharAttribute> attributes;
    private HashMap<String, CharSkill> skills;
    private Card currentTurnCard;
    private Player player;
    private Team team;
    private CharacterSheet sheet;

    public void drawTurnOrder(Deck deck) {
        currentTurnCard = deck.draw();
    }

    public Card getTurnOrder() {
        return currentTurnCard;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Action> getAvailableActions() {
        return new ArrayList<>();
    }

    public boolean isAlly(Character character) {
        return team.isAlly(character.team);
    }

    public void setAttribute(String attr, String value) {
        if (!this.sheet.isUnique)
            throw new TryingChangeNonUniqueCharacter();

        this.sheet.attributes.put(attr, value);
    }

    public static class TryingChangeNonUniqueCharacter extends RuntimeException {
        public TryingChangeNonUniqueCharacter() {
            super("Trying to change non unique character attributes. Change its template sheet instead");
        }
    }
}
