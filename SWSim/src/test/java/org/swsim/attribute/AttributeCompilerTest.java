package org.swsim.attribute;

import org.junit.jupiter.api.Test;
import org.swsim.character.Character;

import static org.junit.jupiter.api.Assertions.*;

class AttributeCompilerTest {
    @Test
    public void compileSimpleAttribute() {
        Attribute a = new Attribute("Str + d4");
        Character c = new Character();
        c.setAttribute("Str", new Attribute("d12 + 1"));
        Attribute compiled = new AttributeCompiler(c).compile(a);
        assertEquals("d12 +1 +d4", compiled.toString());
    }
}