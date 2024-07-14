package org.swsim.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Die {
    int faces;
    boolean lastRollWasAced;
    List<Integer> aceRollValues;
    Random generator;
    int result;

    public Die(int faces, int seed) {
        this.faces = faces;
        generator = new Random(seed);
    }

    public Die(int faces) {
        this(faces, 0);
    }

    public int roll() {
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
