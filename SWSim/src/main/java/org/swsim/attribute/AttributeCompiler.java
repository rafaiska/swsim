package org.swsim.attribute;

import org.swsim.character.Character;
import org.swsim.core.RollToken;
import org.swsim.core.RollTokenType;

import java.util.ArrayList;
import java.util.HashSet;

public class AttributeCompiler {
    Character character;
    HashSet<Attribute> visitedAttrs = new HashSet<>();
    ArrayList<Attribute> searchStack = new ArrayList<>();

    public AttributeCompiler(Character character) {
        this.character = character;
    }

    public void compile(Attribute attribute) {
        if (attribute.isCompiled())
            return;
        visitedAttrs.clear();
        searchStack.clear();
        searchStack.add(attribute);

        while (!searchStack.isEmpty()) {
            Attribute stackAttribute = searchStack.removeLast();
            visitedAttrs.add(stackAttribute);
            if (!stackAttribute.isCompiled())
                compileAttr(stackAttribute);
        }

        for (Attribute a: visitedAttrs)
            a.setCompiled(true);
    }

    private void compileAttr(Attribute stackAttribute) {
        for (RollToken attrToken : stackAttribute.getRollTokens().stream().filter(T -> T.type == RollTokenType.ATTRIBUTE).toList()) {
            Attribute dependency = character.getAttribute(attrToken.text);
            if (dependency == null)
                throw new RuntimeException("Unable to solve dependency on attribute compilation");
            attrToken.attributeDependency = dependency;
            searchStack.add(dependency);
            visitedAttrs.add(dependency);
        }
    }
}
