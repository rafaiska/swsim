package org.swsim.combat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.swsim.character.Character;
import org.swsim.character.CharacterBuilder;
import org.swsim.character.CharacterSheet;
import org.swsim.core.RandomSeedManager;
import org.swsim.core.WorkspaceTest;
import org.swsim.utils.LoggerConfig;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

class CombatSimulationTest extends WorkspaceTest {
    CombatSimulation sim;
    Team teamA;
    Team teamB;
    HashMap<String, CharacterBuilder> charBuilders;

    public CombatSimulationTest() {
        charBuilders = new HashMap<>();
        Logger.getLogger(LoggerConfig.LOGGER_NAME).setLevel(Level.INFO);
    }

    @BeforeEach
    public void configure() {
        RandomSeedManager.getInstance().setSeed(1);
        sim = new CombatSimulation();
        sim.addCharacter(buildMainChar());
        for (int i = 0; i<5; ++i) {
            sim.addCharacter(buildGrunt());
        }
    }

    @Test
    public void testSimpleCombat() {
        sim.start();
    }

    private Character buildGrunt() {
        return buildChar("Grunt", teamB);
    }

    private Character buildMainChar() {
        return buildChar("Passoca", teamA);
    }

    private Character buildChar(final String charName, final Team team) {
        CharacterSheet sheet = getWorkspace().characterSheets.get(charName);
        if (!charBuilders.containsKey(charName))
            charBuilders.put(charName, new CharacterBuilder(sheet));
        CharacterBuilder builder = charBuilders.get(charName);
        return builder.setTeam(team).build();
    }
}