package org.swsim.core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DiceRoll {
    public DiceRoll () {
        dice = new ArrayList<>();
        mod = 0;
        sign = 1;
    }

    public DiceRoll addDie(int faces, boolean aced, int sign) {
        dice.add(new Die(faces, aced, sign));
        return this;
    }

    public DiceRoll addDie(int faces) {
        addDie(faces, false, 1);
        return this;
    }

    public int roll() {
        int accumulator = 0;
        for (Die d: dice) {
            accumulator += d.roll();
        }
        result = (accumulator * sign) + mod;
        return result;
    }

    public void reset() {
        dice.clear();
    }

    public void setSign(int sign) {
        this.sign = sign;
    }

    public void setModifier(int mod) {
        this.mod = mod;
    }

    public int getResult() {
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(sign >= 0 ? "+" : "-");
        builder.append("(");
        builder.append(dice.isEmpty() ? "" : dice.stream().map(Die::toString).collect(Collectors.joining(" +")));
        if (mod != 0) {
            builder.append(mod > 0 ? " +" : " ");
            builder.append(mod);
        }
        builder.append(")");
        return builder.toString();
    }

    private final List<Die> dice;
    private int sign;
    private int mod;
    private int result;

    public void addMod(int mod) {
        this.mod += mod;
    }

    public int getRaisesAgainst(int target) {
        return Math.floorDiv(result - target, 4);
    }
}
