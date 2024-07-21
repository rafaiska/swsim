package org.swsim.attribute;

import org.swsim.core.DiceRoll;
import org.swsim.core.RollParser;
import org.swsim.core.RollToken;
import org.swsim.core.RollTokenType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AttributeRoller {
    private Attribute attribute;

    public AttributeRoller(Attribute a) {
        attribute = a;
    }

    public DiceRoll getDiceRoll() {
        if (!attribute.isCompiled())
            throw new RuntimeException("Can't roll not compiled attribute");
        RollParser parser = new RollParser(attribute.getRollText());
        parser.parse();
        DiceRoll roll = new DiceRoll();
        buildRoll(parser, roll);
        roll.roll();
        return roll;
    }

    private void buildRoll(RollParser parser, DiceRoll roll) {
        List<List<RollToken>> groupStack = new ArrayList<>();
        List<RollToken> currentGroup = new ArrayList<>();
        for (RollToken t: parser.getTokens()) {
            if (t.type == RollTokenType.OP_PARENTHESIS) {
                groupStack.add(currentGroup);
                currentGroup = new ArrayList<>();
                currentGroup.add(t);
            }
            else if (t.type == RollTokenType.CLOSE_PARENTHESIS) {
                currentGroup.add(t);
                processGroup(currentGroup, roll);
                if (groupStack.isEmpty())
                    continue;
                currentGroup = groupStack.removeLast();
            }
            else {
                currentGroup.add(t);
            }
        }

        if(!currentGroup.isEmpty())
            processGroup(currentGroup, roll);

        if(!groupStack.isEmpty())
            throw new RuntimeException("Roll build error");
    }

    private void processGroup(List<RollToken> tokenGroup, DiceRoll roll) {
        int currentSign = 1;
        int parenthesisBalancer = 0;
        for (RollToken t: tokenGroup) {
            switch (t.type) {
                case OP_PARENTHESIS -> parenthesisBalancer += 1;
                case CLOSE_PARENTHESIS -> parenthesisBalancer -= 1;
                case ARITHMETIC_OP -> currentSign = getSign(t);
                case DICE -> addDice(t, currentSign, roll);
                case MODIFIER -> roll.addMod(currentSign * Integer.parseInt(t.text));
            }
        }
        if (parenthesisBalancer != 0)
            throw new RuntimeException("Unbalanced parenthesis found");
    }

    private int getSign(RollToken t) {
        if (Objects.equals(t.text, "+"))
            return 1;
        else if (Objects.equals(t.text, "-"))
            return -1;
        else
            throw new RuntimeException("Invalid arithmetic operator");
    }

    private void addDice(RollToken token, int sign, DiceRoll roll) {
        Matcher matcher = Pattern.compile("da?").matcher(token.text);
        if (!matcher.find())
            throw new RuntimeException("Invalid dice token");

        boolean aced = matcher.group().equals("da");
        String numberOfDiceText = token.text.substring(0, matcher.start());
        int numberOfDice = numberOfDiceText.isEmpty() ? 1 : Integer.parseInt(numberOfDiceText);
        int numberOfFaces = Integer.parseInt(token.text.substring(matcher.end()));

        for (int i=0; i<numberOfDice; ++i)
            roll = roll.addDie(numberOfFaces, aced, sign);
    }
}
