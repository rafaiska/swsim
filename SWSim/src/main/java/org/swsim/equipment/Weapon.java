package org.swsim.equipment;

import org.swsim.action.Action;
import org.swsim.action.Attack;
import org.swsim.attribute.Attribute;

public class Weapon extends Equipment {

    private Attribute attack;
    private Attribute damage;
    private Attribute range;

    @Override
    public Action spawnUseAction() {
        return new Attack(this.attack, this.damage);
    }

    public void setDamage(Attribute damage) {
        this.damage = damage;
    }

    public Attribute getDamage() {
        return damage;
    }

    public Attribute getRange() {
        return range;
    }

    public void setRange(Attribute range) {
        this.range = range;
    }

    public void setAttackRoll(Attribute attack) {
        this.attack = attack;
    }

    public Attribute getAttackAttribute() {
        return attack;
    }
}
