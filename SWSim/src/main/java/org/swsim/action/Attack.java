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
        attackRoll.roll();
        if (wasRaised())
            addRaiseBonusToDamageRoll();
        damageRoll.roll();
    }

    public boolean wasRaised() {
        int margin = attackRoll.getResult() - target.getAttribute("Parry").getResult();
        return (margin / 4) >= 1;
    }

    private void addRaiseBonusToDamageRoll() {
        damageRoll.appendToValue("+d6");
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

    public int getAttackResult() {
        return attackRoll.getResult();
    }

    public int getDamageResult() {
        return damageRoll.getResult();
    }

    private Character attacker;
    private Character target;
    private Attribute damageRoll;
    private Attribute attackRoll;
}
