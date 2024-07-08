package org.swsim.character;

import org.swsim.core.Card;
import org.swsim.core.Deck;
import org.swsim.player.Player;

import java.applet.Applet;
import java.util.HashMap;

public class Character {
    private HashMap<String, CharAttribute> attributes;
    private HashMap<String, CharSkill> skills;
    private Card currentTurnCard;
    private Player player;

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
}
