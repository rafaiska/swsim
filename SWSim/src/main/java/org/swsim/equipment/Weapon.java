package org.swsim.equipment;

import org.swsim.action.Action;
import org.swsim.action.Attack;

public class Weapon extends Equipment {

    private String attributeRoll;
    private String skillName;

    @Override
    public Action spawnUseAction() {
        return new Attack(this.attributeRoll, this.skillName);
    }
}
