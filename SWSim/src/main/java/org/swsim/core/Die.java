package org.swsim.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Die {
    int faces;
    boolean aced;
    int sign;
    boolean lastRollWasAced;
    List<Integer> aceRollValues;
    Random generator;
    int result;

    public Die(int faces, boolean aced, int sign) {
        this.aced = aced;
        this.faces = faces;
        this.sign = sign;
        generator = new Random(RandomSeedManager.getInstance().getSeed());
    }

    public int roll() {
        return aced ? rollWithAces() : rollWoAces();
    }

    public int rollWoAces() {
        lastRollWasAced = false;
        result = (Math.abs(generator.nextInt()) % faces) + 1;
        return result;
    }

    public int rollWithAces() {
        lastRollWasAced = true;
        int accumulator = 0;
        aceRollValues = new ArrayList<>();
        while (true) {
            int roll = roll();
            aceRollValues.add(roll);
            accumulator += roll;
            if (roll != faces)
                break;
        }
        result = accumulator;
        return result;
    }

    public String printResult() {
        return String.format("%d (d%d%s)", result, faces, lastRollWasAced ? "!" : "");
    }
}
