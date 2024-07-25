package org.swsim.combat;

import org.swsim.action.Action;
import org.swsim.character.Character;
import org.swsim.core.Deck;
import org.swsim.utils.LoggerConfig;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CombatSimulation {
    public void addCharacter(Character character) {
        if(characters == null)
            characters = new ArrayList<>();
        characters.add(character);
    }

    public void start() {
        currentTurn = 0;
        logSimulationInfo("Simulation started");
        gameLoop();
        logSimulationInfo("Simulation ended");
    }

    private void gameLoop() {
        while (!isGameOver()) {
            logSimulationInfo(String.format("Turn %d started!", currentTurn));
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
        HashSet<Team> remainingTeams = new HashSet<>();
        for (Character c: characters.stream().filter(C -> !C.isIncapacitated()).toList()) {
            remainingTeams.add(c.getTeam());
            if (remainingTeams.size() > 1)
                return false;
        }
        return true;
    }

    private void logSimulationInfo(String msg) {
        Logger.getLogger(LoggerConfig.LOGGER_NAME).log(Level.INFO, msg);
    }

    private List<Character> characters;
    private Deck deck;
    private int currentTurn;
}
