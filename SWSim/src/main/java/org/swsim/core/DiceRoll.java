package org.swsim.core;

import java.util.ArrayList;
import java.util.List;

public class DiceRoll {
    public DiceRoll (int seed, boolean aces) {
        this.seed = seed;
        this.aces = aces;
        dice = new ArrayList<>();
    }

    public DiceRoll addDie(int faces) {
        dice.add(new Die(faces, seed));
        return this;
    }

    public int roll() {
        if (dice.isEmpty())
            throw new DiceRollWODice();
        int accumulator = 0;
        for (Die d: dice) {
            accumulator += aces ? d.rollWithAces() : d.roll();
        }
        result = accumulator;
        return result;
    }

    public void reset() {
        dice.clear();
    }

    private final int seed;
    private final List<Die> dice;
    private final boolean aces;
    private int result;

    public Object getResult() {
        return result;
    }

    public static class DiceRollWODice extends RuntimeException {}
}
