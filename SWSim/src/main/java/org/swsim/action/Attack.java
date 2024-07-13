package org.swsim.action;

import org.swsim.attribute.Attribute;
import org.swsim.attribute.AttributeCompiler;
import org.swsim.character.Character;

public class Attack implements Action {
    public Attack(Attribute attack, Attribute damage) {
        this.damageRoll = damage;
        this.attackRoll = attack;
    }

    @Override
    public void execute() {
    }

    @Override
    public void setActor(Character character) {
        attacker = character;
        compileAttributes();
    }

    private void compileAttributes() {
        AttributeCompiler compiler = new AttributeCompiler(attacker);
        this.attackRoll = compiler.compile(attackRoll);
        this.damageRoll = compiler.compile(damageRoll);
    }

    public void setTarget(Character target) {
        this.target = target;
    }

    public Character getAttacker() {
        return attacker;
    }

    public Character getTarget() {
        return target;
    }

    public Attribute getDamageRoll() {
        return damageRoll;
    }

    public void setDamageRoll(Attribute damageRoll) {
        this.damageRoll = damageRoll;
    }

    public Attribute getAttackRoll() {
        return attackRoll;
    }

    public void setAttackRoll(Attribute attackRoll) {
        this.attackRoll = attackRoll;
    }

    private Character attacker;
    private Character target;
    private Attribute damageRoll;
    private Attribute attackRoll;
}
