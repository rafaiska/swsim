package org.swsim.core;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RollParserTest {
    @BeforeAll
    static void configureSeed() {
        RandomSeedManager.getInstance().setSeed(1);
    }

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
        assertEquals(3, parser.getTokens().size());

        assertEquals(RollTokenType.DICE, parser.getTokens().get(0).type);
        assertEquals(0, parser.getTokens().get(0).startinPos);
        assertEquals(2, parser.getTokens().get(0).endingPos);
        assertEquals("d8", parser.getTokens().get(0).text);

        assertEquals(RollTokenType.ARITHMETIC_OP, parser.getTokens().get(1).type);
        assertEquals(2, parser.getTokens().get(1).startinPos);
        assertEquals(3, parser.getTokens().get(1).endingPos);
        assertEquals("+", parser.getTokens().get(1).text);

        assertEquals(RollTokenType.MODIFIER, parser.getTokens().get(2).type);
        assertEquals(3, parser.getTokens().get(2).startinPos);
        assertEquals(4, parser.getTokens().get(2).endingPos);
        assertEquals("2", parser.getTokens().get(2).text);
    }

    @Test
    public void rollTokenOrder() {
        RollToken a = new RollToken();
        a.startinPos = 4;
        a.endingPos = 8;

        RollToken b = new RollToken();
        b.startinPos = 0;
        b.endingPos = 4;
        assertTrue(a.compareTo(b) > 0);

        b.startinPos = 2;
        b.endingPos = 6;
        assertEquals(0, a.compareTo(b));

        b.startinPos = 4;
        b.endingPos = 12;
        assertEquals(0, a.compareTo(b));

        b.startinPos = 8;
        assertTrue(a.compareTo(b) < 0);
    }
}