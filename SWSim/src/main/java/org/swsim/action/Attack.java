package org.swsim.action;

import org.swsim.attribute.AttributeCompiler;
import org.swsim.character.Character;

public class Attack implements Action {
    public Attack(String attribute, String skill) {
        this.damageRoll = attribute;
        this.attackRoll = skill;
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

    public String getDamageRoll() {
        return damageRoll;
    }

    public void setDamageRoll(String damageRoll) {
        this.damageRoll = damageRoll;
    }

    public String getAttackRoll() {
        return attackRoll;
    }

    public void setAttackRoll(String attackRoll) {
        this.attackRoll = attackRoll;
    }

    private Character attacker;
    private Character target;
    private String damageRoll;
    private String attackRoll;
}
