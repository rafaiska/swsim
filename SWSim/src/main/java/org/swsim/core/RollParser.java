package org.swsim.core;

import java.util.ArrayList;
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

    public void execute() {
        createRolls();
        for (DiceRoll roll: diceRolls.values()) {
            roll.roll();
        }
    }

    private void createRolls() {
        for (RollToken token: tokens) {
            if (!diceRolls.containsKey(token)) {
                switch (token.type) {
                    case DICE -> createDiceRoll(token);
                    case MODIFIER -> createModiferRoll(token);
                    case ATTRIBUTE -> throw new RuntimeException("Cannot create roll for unassigned attributes");
                }
            }
        }
    }

    private void createDiceRoll(RollToken token) {
        int sign = token.text.getBytes()[0] == '-' ? -1 : 1;

        String rollTextWOSign = token.text.replaceAll("[+-]", "");
        String[] values = token.text.split("d");
        int numberOfDice = !values[0].isEmpty() ? Math.abs(Integer.parseInt(values[0])) : 1;
        int numberOfFaces = Integer.parseInt(values[1]);

        DiceRoll diceRoll = new DiceRoll(0, false);
        for (int i=0; i<numberOfDice; ++i)
            diceRoll.addDie(numberOfFaces);
        diceRoll.setSign(sign);
        diceRolls.put(token, diceRoll);
    }


    private void createModiferRoll(RollToken token) {
        DiceRoll diceRoll = new DiceRoll(0, false);
        diceRoll.setModifier(Integer.parseInt(token.text));
        diceRolls.put(token, diceRoll);
    }

    public String printResult() {
        StringBuilder ret = new StringBuilder();
        for (RollToken token: tokens)
            ret.append(diceRolls.get(token).printResult()).append(" ");
        ret.append("= ");
        ret.append((Integer) diceRolls.values().stream().mapToInt(DiceRoll::getResult).sum());
        return ret.toString();
    }
}
