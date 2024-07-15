package org.swsim.attribute;

import org.junit.jupiter.api.Test;
import org.swsim.character.Character;

import static org.junit.jupiter.api.Assertions.*;

class AttributeCompilerTest {
    @Test
    public void compileSimpleAttribute() {
        Attribute a = new Attribute("Str");
        Character c = new Character();
        c.setAttribute("Str", new Attribute("d6"));
        Attribute compiled = new AttributeCompiler(c).compile(a);
        assertEquals("d6", compiled.getValue());
    }
}