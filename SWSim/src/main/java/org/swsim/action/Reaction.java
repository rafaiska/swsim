package org.swsim.action;

public interface Reaction {
    public Action react(Action opposingAction);
    public boolean reactsTo(Action opposingAction);
}
