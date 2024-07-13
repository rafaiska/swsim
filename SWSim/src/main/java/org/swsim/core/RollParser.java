package org.swsim.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

public class RollParser {
    private String rollText;
    private List<RollToken> tokens;
    private HashMap<RollToken, DiceRoll> diceRolls;
    boolean readyToRoll = false;

    public RollParser(String rollText) {
        this.rollText = rollText;
    }

    public void parse() {
        tokens = new ArrayList<>();
        diceRolls = new HashMap<>();
        readyToRoll = false;
        preProcess();
        tokenize();
        if (attributesFound().isEmpty()) {
            computeDiceRollTokens();
            readyToRoll = true;
        }
    }

    private void computeDiceRollTokens() {
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
        int cursor = 0;
        RollToken previousToken = null;
        RollToken nextToken = null;

        while (cursor < tokens.size() && tokens.get(cursor).startinPos <= newToken.startinPos)
        {
            previousToken = tokens.get(cursor);
            ++cursor;
            if (cursor < tokens.size())
                nextToken = tokens.get(cursor);
        }

        if (previousToken != null && previousToken.endingPos > newToken.startinPos)
            return;

        if (nextToken != null && nextToken.startinPos < newToken.endingPos)
            return;

        tokens.add(cursor, newToken);
    }

    public int rollForResult() {
        if (!canRoll())
            throw new RuntimeException("Cannot roll: " + rollText);
        return 0;
    }

    private void preProcess() {
        rollText = rollText.replaceAll("[ \t\n\r]", "");
    }

    public List<String> attributesFound() {
        return tokens.stream().filter(T -> T.type.equals(RollTokenType.ATTRIBUTE)).map((T) -> T.text).collect(Collectors.toList());
    }

    public boolean canRoll() {
        return readyToRoll;
    }

    public List<DiceRoll> getDiceRolls() {
        return new ArrayList<>();
    }

    public List<RollToken> getTokens() {
        return tokens;
    }
}
