package org.swsim.attribute;

import org.swsim.character.Character;

public class AttributeCompiler {
    Character character;

    public AttributeCompiler(Character character) {
        this.character = character;
    }

    public static boolean isCompiled(Attribute attribute) {
        return attribute.getRollAttributes().isEmpty();
    }

    public Attribute compile(Attribute attribute) {
        Attribute ret = new Attribute(attribute.getMaxValue());
        for (String attrTokenName: attribute.getRollAttributes()) {
            Attribute charAttr = character.getAttribute(attrTokenName);
            if (charAttr == null)
                throw new RuntimeException("Character does not have enough attributes to compile");
            ret.fillAttributeRoll(attrTokenName, charAttr);
        }
        return ret;
    }
}
