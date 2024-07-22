package org.swsim.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Die {
    int faces;
    boolean aced;
    int sign;
    List<Integer> aceRollValues;
    Random generator;
    int result;

    public Die(int faces, boolean aced, int sign) {
        this.aced = aced;
        this.faces = faces;
        this.sign = sign;
        generator = new Random(RandomSeedManager.getInstance().getSeed());
    }

    public Die(int faces, boolean aced) {
        this(faces, aced, 1);
    }

    public int roll() {
        return aced ? rollWithAces() : rollWoAces();
    }

    public int rollWoAces() {
        result = (Math.abs(generator.nextInt()) % faces) + 1;
        return result;
    }

    public int rollWithAces() {
        int accumulator = 0;
        aceRollValues = new ArrayList<>();
        while (true) {
            int roll = rollWoAces();
            aceRollValues.add(roll);
            accumulator += roll;
            if (roll != faces)
                break;
        }
        result = accumulator;
        return result;
    }

    @Override
    public String toString() {
        return String.format("%d (d%s%d%s)", result, aced ? "a" : "", faces, result > faces ? "!" : "");
    }
}
