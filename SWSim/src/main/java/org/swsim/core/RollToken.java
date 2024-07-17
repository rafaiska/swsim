package org.swsim.core;

import org.swsim.attribute.Attribute;

public class RollToken implements Comparable<RollToken>{
    public String text;
    public int startinPos;
    public int endingPos;
    public RollTokenType type;
    public Attribute attributeDependency;

    @Override
    public int compareTo(RollToken o) {
        if (this.endingPos <= o.startinPos)
            return -1;
        else if (this.startinPos >= o.endingPos)
            return 1;
        else
            return 0;
    }
}
