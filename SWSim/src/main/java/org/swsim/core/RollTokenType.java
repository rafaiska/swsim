package org.swsim.core;

import java.util.regex.Pattern;

public enum RollTokenType {
    DICE(Pattern.compile("[0-9]*d[0-9]+")),
    ARITHMETIC_OP(Pattern.compile("[+*-]")),
    OP_PARENTHESIS(Pattern.compile("[(]")),
    CLOSE_PARENTHESIS(Pattern.compile("[)]")),
    MODIFIER(Pattern.compile("[0-9]+"));

    RollTokenType(Pattern pattern) {this.pattern = pattern;};

    final public Pattern pattern;
}