package org.swsim.equipment;

import org.swsim.action.Action;

public abstract class Equipment {
    protected String name;

    public String getName() {return name;}
    public abstract Action spawnUseAction();
}
