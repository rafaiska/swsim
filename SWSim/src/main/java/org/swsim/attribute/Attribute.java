package org.swsim.attribute;

import org.swsim.core.RollParser;
import org.swsim.core.RollToken;
import org.swsim.core.RollTokenType;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Attribute {
    private String value;
    private String maxValue;
    private String lastRollValue;
    private RollParser rollParser;
    private int result;
    private boolean isCompiled;

    public Attribute(String maxValue) {
        this.maxValue = maxValue;
        this.value = this.maxValue;
        this.isCompiled = false;
    }

    public void roll() {
        result = getRollParser().rollForResult();
    }

    private RollParser getRollParser() {
        if (needsToUpdateRollParser()) {
            rollParser = new RollParser(value);
            rollParser.parse();
            lastRollValue = value;
        }
        return rollParser;
    }

    private boolean needsToUpdateRollParser() {
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

    public String getMaxValue() {
        return maxValue;
    }

    public List<String> getAttributeDependencies() {
        return getRollParser().attributesFound();
    }

    public List<RollToken> getRollTokens() {
        return getRollParser().getTokens();
    }

    @Override
    public String toString() {
        return getRollTokens().stream().map(this::getDescription).collect(Collectors.joining(" "));
    }

    private String getDescription(RollToken token) {
        if (token.type == RollTokenType.ATTRIBUTE)
            return String.format("(%s)(%s)", token.attributeDependency.toString(), token.text);
        else
            return token.text;
    }

    public boolean isCompiled() {
        return isCompiled;
    }

    public void setCompiled(boolean compiled) {
        isCompiled = compiled;
    }
}
