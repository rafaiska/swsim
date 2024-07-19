package org.swsim.action;

import org.swsim.attribute.Attribute;
import org.swsim.attribute.AttributeCompiler;
import org.swsim.character.Character;

public class Attack implements Action {
    public Attack(Attribute attack, Attribute damage) {
        this.damageAttr = damage;
        this.attackAttr = attack;
    }

    @Override
    public void execute() {
        AttributeRoller roller = new AttributeRoller(attackAttr);
        roller.roll();
        if (wasRaised(roller))
            addRaiseBonusToDamageRoll();
        damageAttr.roll();
    }

    public boolean wasRaised(AttributeRoller roller) {
        int defense = isMelee ? target.getAttribute("Parry").castToInt() : 4;
        int raises = roller.getRaisesAgainst(defense);
        return raises >= 1;
    }

    private void addRaiseBonusToDamageRoll() {
        damageAttr.appendToValue("+d6");
    }

    @Override
    public void setActor(Character character) {
        attacker = character;
        compileAttributes();
    }

    private void compileAttributes() {
        AttributeCompiler compiler = new AttributeCompiler(attacker);
        compiler.compile(attackAttr);
        compiler.compile(damageAttr);
    }

    public void setTarget(Character target) {
        this.target = target;
    }

    public int getAttackResult() {
        return attackResult;
    }

    public int getDamageResult() {
        return damageResult;
    }

    private Character attacker;
    private Character target;
    private final Attribute damageAttr;
    private final Attribute attackAttr;
    private int attackResult;
    private String attackResultString;
    private boolean wasRaised;
    private int damageResult;
    private String damageResultString;
}
