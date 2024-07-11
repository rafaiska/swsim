package org.swsim.equipment;

import org.swsim.action.Action;
import org.swsim.action.Attack;

public class Weapon extends Equipment {

    private String attributeRoll;
    private String skillName;
    private String damage;
    private Integer range;

    @Override
    public Action spawnUseAction() {
        return new Attack(this.attributeRoll, this.skillName);
    }

    public void setDamage(String damage) {
    }

    public String getDamage() {
        return damage;
    }

    public Integer getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }
}
