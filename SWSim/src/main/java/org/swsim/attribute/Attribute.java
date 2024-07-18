package org.swsim.attribute;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Attribute {
    private String value;
    private boolean isCompiled;
    private HashMap<String, AttributeToken> tokens;

    public Attribute(String value) {
        this.value = value;
        this.isCompiled = false;
        mapAllTokens();
    }

    private void mapAllTokens() {
        tokens = new HashMap<>();
        Matcher attrMatcher = Pattern.compile("[+-]?[a-zA-Z]+").matcher(value);
        while (attrMatcher.find()) {
            AttributeToken newToken = new AttributeToken();
            newToken.tokenText = value.substring(attrMatcher.start(), attrMatcher.end());
            tokens.put(newToken.tokenText, newToken);
        }
    }

    public String getValue() {
        return value;
    }

    public void appendToValue(String increment) {
        value += increment;
        value += increment;
    }

    public boolean isCompiled() {
        return isCompiled;
    }

    public void setCompiled(boolean compiled) {
        isCompiled = compiled;
    }
}
