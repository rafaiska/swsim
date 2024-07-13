package org.swsim.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RollParserTest {
    @Test
    public void parseSimpleRoll() {
        RollParser parser = new RollParser("3d6");
        parser.parse();
        assertEquals(1, parser.getTokens().size());
        assertEquals(RollTokenType.DICE, parser.getTokens().get(0).type);
        assertEquals(0, parser.getTokens().get(0).startinPos);
        assertEquals(3, parser.getTokens().get(0).endingPos);
        assertEquals("3d6", parser.getTokens().get(0).text);
    }

    @Test
    public void parseRollWithModifier() {
        RollParser parser = new RollParser("d8 + 2");
        parser.parse();
        assertEquals(2, parser.getTokens().size());

        assertEquals(RollTokenType.DICE, parser.getTokens().get(0).type);
        assertEquals(0, parser.getTokens().get(0).startinPos);
        assertEquals(2, parser.getTokens().get(0).endingPos);
        assertEquals("d8", parser.getTokens().get(0).text);

        assertEquals(RollTokenType.MODIFIER, parser.getTokens().get(1).type);
        assertEquals(2, parser.getTokens().get(1).startinPos);
        assertEquals(4, parser.getTokens().get(1).endingPos);
        assertEquals("+2", parser.getTokens().get(1).text);
    }

    @Test
    public void parseRollWithAttribute() {
        RollParser parser = new RollParser("Strength + 2d4");
        parser.parse();
        assertEquals(2, parser.getTokens().size());

        assertEquals(RollTokenType.ATTRIBUTE, parser.getTokens().get(0).type);
        assertEquals(0, parser.getTokens().get(0).startinPos);
        assertEquals(8, parser.getTokens().get(0).endingPos);
        assertEquals("Strength", parser.getTokens().get(0).text);

        assertEquals(RollTokenType.DICE, parser.getTokens().get(1).type);
        assertEquals(8, parser.getTokens().get(1).startinPos);
        assertEquals(12, parser.getTokens().get(1).endingPos);
        assertEquals("+2d4", parser.getTokens().get(1).text);
    }
}