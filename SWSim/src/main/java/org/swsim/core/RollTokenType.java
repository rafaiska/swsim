package org.swsim.core;

import java.util.regex.Pattern;

public enum RollTokenType {
    DICE(Pattern.compile("[+-]?[0-9]*d[0-9]+")),
    MODIFIER(Pattern.compile("[+-]?[0-9]+")),
    ATTRIBUTE(Pattern.compile("[+-]?[a-zA-Z]+"));

    RollTokenType(Pattern pattern) {this.pattern = pattern;};

    final public Pattern pattern;
}