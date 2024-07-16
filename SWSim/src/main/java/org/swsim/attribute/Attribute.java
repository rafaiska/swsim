package org.swsim.attribute;

import org.swsim.core.RollParser;
import org.swsim.core.RollToken;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Attribute {
    private String value;
    private String maxValue;
    private String lastRollValue;
    private RollParser rollParser;
    private int result;

    public Attribute(String maxValue) {
        this.maxValue = appendPlusIfNecessary(maxValue);
        this.value = this.maxValue;
    }

    private String appendPlusIfNecessary(String value) {
        if (Pattern.matches("[+-].+", value))
            return value;
        else
            return "+" + value;
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

    public List<String> getRollAttributes() {
        return getRollParser().attributesFound();
    }

    public void fillAttributeRoll(String attributeName, Attribute attribute) {
        getRollParser().replaceAttributeToken(attributeName, attribute);
    }

    public List<RollToken> getRollTokens() {
        return getRollParser().getTokens();
    }

    public String getCompiledValue() {
        return getRollTokens().stream().map(T -> T.text).collect(Collectors.joining(" "));
    }
}
