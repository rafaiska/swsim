package org.swsim.core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DiceRoll {
    public DiceRoll (boolean aces) {
        this.aces = aces;
        dice = new ArrayList<>();
    }

    public DiceRoll addDie(int faces) {
        dice.add(new Die(faces));
        return this;
    }

    public int roll() {
        int accumulator = 0;
        for (Die d: dice) {
            accumulator += aces ? d.rollWithAces() : d.roll();
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

    public String printResult() {
        String signStr = result >= 0 ? "+" : "-";
        String diceStr = dice.isEmpty() ? "" : dice.stream().map(D -> signStr + D.printResult()).collect(Collectors.joining(" "));
        String modStr = mod != 0 ? String.valueOf(mod) : "";
        return diceStr + modStr;
    }

    private final List<Die> dice;
    private final boolean aces;
    private int sign = 1;
    private int mod = 0;
    private int result;
}
