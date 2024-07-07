package org.swsim.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Die {
    int faces;
    List<Integer> aceRollValues;
    Random generator;

    public Die(int faces, int seed) {
        this.faces = faces;
        generator = new Random(seed);
    }

    public Die(int faces) {
        this(faces, 0);
    }

    public int roll() {
        return (Math.abs(generator.nextInt()) % faces) + 1;
    }

    public int rollWithAces() {
        int accumulator = 0;
        aceRollValues = new ArrayList<>();
        while (true) {
            int roll = roll();
            aceRollValues.add(roll);
            accumulator += roll;
            if (roll != faces)
                break;
        }
        return accumulator;
    }
}
