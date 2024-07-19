package org.swsim.attribute;

import org.swsim.character.Character;
import org.swsim.core.RollToken;
import org.swsim.core.RollTokenType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AttributeCompiler {
    Character character;
    HashSet<Attribute> visitedAttrs = new HashSet<>();
    ArrayList<Attribute> searchStack = new ArrayList<>();

    final HashSet<String> BLACKLISTED_TOKENS = new HashSet<>(List.of("d", "da"));

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
            if (!visitedAttrs.contains(stackAttribute)) {
                visitedAttrs.add(stackAttribute);
                if (!stackAttribute.isCompiled())
                    compileAttr(stackAttribute);
            }
        }

        for (Attribute a: visitedAttrs)
            a.setCompiled(true);
    }

    private void compileAttr(Attribute attribute) {
        HashMap<String, AttributeToken> tokens = mapAllTokens(attribute);
        for (String dependencyName: tokens.keySet()) {
            Attribute dependency = character.getAttribute(dependencyName);
            if (dependency == null)
                throw new RuntimeException("Unable to solve dependency on attribute compilation");
            attribute.addDependency(dependencyName, dependency);
            searchStack.add(dependency);
        }
    }

    private HashMap<String, AttributeToken> mapAllTokens(Attribute attribute) {
        HashMap<String, AttributeToken> tokens = new HashMap<>();
        Matcher attrMatcher = Pattern.compile("[a-zA-Z]+").matcher(attribute.getValue());
        while (attrMatcher.find()) {
            String tokenText = attribute.getValue().substring(attrMatcher.start(), attrMatcher.end());
            if (!BLACKLISTED_TOKENS.contains(tokenText)) {
                AttributeToken newToken = new AttributeToken();
                newToken.tokenText = tokenText;
                tokens.put(newToken.tokenText, newToken);
            }
        }
        return tokens;
    }
}
