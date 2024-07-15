package org.swsim.attribute;

import org.swsim.core.RollParser;

import java.util.Objects;

public class Attribute {
    private String value;
    private String maxValue;
    private String lastRollValue;
    private RollParser rollParser;
    private int result;

    public Attribute(String maxValue) {
        this.maxValue = maxValue;
        this.value = this.maxValue;
    }

    public boolean canRoll() {
        return rollParser.canRoll();
    }

    public int roll() {
        if (needsToUpdateRoll()) {
            rollParser = new RollParser(value);
            rollParser.parse();
            lastRollValue = value;
        }
        result = rollParser.rollForResult();
        return result;
    }

    private boolean needsToUpdateRoll() {
        return !Objects.equals(lastRollValue, value);
    }

    public String getValue() {
        return value;
    }

    public int getResult() {
        return result;
    }

    public void appendToValue(String increment) {
        value += increment;
        maxValue += increment;
    }
}
