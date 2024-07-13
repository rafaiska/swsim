package org.swsim.attribute;

import org.swsim.character.Character;

public class AttributeCompiler {
    Character character;

    public AttributeCompiler(Character character) {
        this.character = character;
    }

    public static boolean isCompiled(Attribute attribute) {
        return true;
    }

    public Attribute compile(Attribute attribute) {
        return new Attribute("d6");
    }
}
