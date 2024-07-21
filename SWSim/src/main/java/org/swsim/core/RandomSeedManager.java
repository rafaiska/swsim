package org.swsim.core;

import java.time.LocalDateTime;

public class RandomSeedManager {
    private static RandomSeedManager instance = null;
    long seed = 0;

    private RandomSeedManager(){}
    public static RandomSeedManager getInstance() {
        if (instance == null)
            instance = new RandomSeedManager();
        return instance;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }

    public long getSeed() {
        return seed == 0 ? System.currentTimeMillis(): seed;
    }
}
