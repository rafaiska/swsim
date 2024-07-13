package org.swsim.character;

import org.swsim.action.Action;
import org.swsim.attribute.Attribute;
import org.swsim.combat.Team;
import org.swsim.core.Card;
import org.swsim.core.Deck;
import org.swsim.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Character {
    private HashMap<String, Attribute> attributes;
    private Card currentTurnCard;
    private Player player;
    private Team team;

    public Character() {
        attributes = new HashMap<>();
    }

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

    public void setAttribute(String attr, Attribute value) {
        attributes.put(attr, value);
    }
}
