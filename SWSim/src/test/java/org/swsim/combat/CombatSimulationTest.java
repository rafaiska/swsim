package org.swsim.combat;

import org.junit.jupiter.api.Test;
import org.swsim.core.RandomSeedManager;
import org.swsim.utils.LoggerConfig;

import java.util.logging.Level;
import java.util.logging.Logger;

class CombatSimulationTest {
    public CombatSimulationTest() {
        Logger.getLogger(LoggerConfig.LOGGER_NAME).setLevel(Level.INFO);
    }

    @Test
    public void testSimpleCombat() {
        RandomSeedManager.getInstance().setSeed(1);
        CombatSimulation sim = new CombatSimulation();
        sim.start();
    }
}