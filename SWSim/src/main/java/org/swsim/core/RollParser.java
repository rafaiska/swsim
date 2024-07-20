package org.swsim.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

public class RollParser {
    private String rollText;
    private List<RollToken> tokens;

    public RollParser(String rollText) {
        this.rollText = rollText;
    }

    public void parse() {
        tokens = new ArrayList<>();
        preProcess();
        tokenize();
    }

    private void tokenize() {
        for (RollTokenType type : RollTokenType.values()) {
            parseTokens(type);
        }
        if (!entireTextWasParsed())
            throw new RuntimeException("Invalid token found in roll: " + rollText);
    }

    private void parseTokens(RollTokenType type) {
        Matcher matcher = type.pattern.matcher(rollText);
        while (matcher.find()) {
            RollToken newToken = new RollToken();
            newToken.type = type;
            newToken.startinPos = matcher.start();
            newToken.endingPos = matcher.end();
            newToken.text = rollText.substring(matcher.start(), matcher.end());
            addNewTokenInOrder(newToken);
        }
    }

    private boolean entireTextWasParsed() {
        return tokens.stream().mapToInt(T -> T.text.length()).sum() == rollText.length();
    }

    private void addNewTokenInOrder(RollToken newToken) {
        if (tokens.isEmpty()) {
            tokens.add(newToken);
            return;
        }

        int cursor = 0;
        RollToken previousToken = null;
        while (cursor < tokens.size() + 1)
        {
            RollToken cursorToken = cursor == tokens.size() ? null : tokens.get(cursor);
            if ((previousToken == null || newToken.compareTo(previousToken) > 0) && (cursorToken == null || newToken.compareTo(cursorToken) < 0)) {
                tokens.add(cursor, newToken);
                break;
            }
            ++cursor;
            previousToken = cursorToken;
        }
    }

    private void preProcess() {
        rollText = rollText.replaceAll("[ \t\n\r]", "");
    }

    public List<RollToken> getTokens() {
        return tokens;
    }
}
