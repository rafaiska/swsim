package org.swsim.equipment;

import org.swsim.action.Action;
import org.swsim.action.Attack;

public class Weapon extends Equipment {

    private String damageRoll;
    private String attackRoll;
    private String damage;
    private Integer range;

    @Override
    public Action spawnUseAction() {
        return new Attack(this.damageRoll, this.attackRoll);
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public String getDamageRoll() {
        return damage;
    }

    public Integer getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public void setAttackRoll(String attackRoll) {
        this.attackRoll = attackRoll;
    }

    public String getAttackRoll() {
        return attackRoll;
    }
}
