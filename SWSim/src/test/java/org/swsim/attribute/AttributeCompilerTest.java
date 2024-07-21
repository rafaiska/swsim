package org.swsim.attribute;

import org.junit.jupiter.api.Test;
import org.swsim.character.Character;

import static org.junit.jupiter.api.Assertions.*;

class AttributeCompilerTest {
    @Test
    public void compileSimpleAttribute() {
        Attribute a = new Attribute("Str + d4");
        Character c = new Character();
        Attribute attributeStrength = new Attribute("d12 + 1");
        c.setAttribute("Str", attributeStrength);
        new AttributeCompiler(c).compile(a);
        assertEquals(1, a.getDependencies().size());
        assertEquals(attributeStrength, a.getDependencies().get("Str"));
    }
}