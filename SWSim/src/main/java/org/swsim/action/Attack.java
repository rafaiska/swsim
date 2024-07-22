package org.swsim.action;

import org.swsim.attribute.Attribute;
import org.swsim.attribute.AttributeCompiler;
import org.swsim.attribute.AttributeRoller;
import org.swsim.character.Character;
import org.swsim.core.DiceRoll;

import java.util.ArrayList;

public class Attack implements Action {
    public Attack(Attribute attack, Attribute damage) {
        isMelee = true;
        this.damageAttr = damage;
        this.attackAttr = attack;
    }

    @Override
    public void execute() {
        AttributeCompiler compiler = new AttributeCompiler(attacker);
        compiler.compile(attackAttr);
        compiler.compile(damageAttr);
        makeAttackRoll();
        makeDamageRoll();
    }

    private void makeDamageRoll() {
        AttributeRoller damageRoller = new AttributeRoller(attackAttr);
        damageRoll = damageRoller.getDiceRoll();
        if (wasRaised()) {
            makeBonusDamageRoll("d6");
        }
    }

    private void makeBonusDamageRoll(String rollText) {
        Attribute bonusDamage = new Attribute(rollText);
        new AttributeCompiler(attacker).compile(bonusDamage);
        bonusDamageRoll = new AttributeRoller(bonusDamage).getDiceRoll();
    }

    private void makeAttackRoll() {
        AttributeRoller attackRoller = new AttributeRoller(attackAttr);
        attackRoll = attackRoller.getDiceRoll();
    }

    public boolean wasRaised() {
        int defense = isMelee ? target.getAttribute("Parry").castToInt() : 4;
        int raises = attackRoll.getRaisesAgainst(defense);
        return raises >= 1;
    }

    @Override
    public void setActor(Character character) {
        attacker = character;
        compileAttributes(attacker);
    }

    private void compileAttributes(Character character) {
        AttributeCompiler compiler = new AttributeCompiler(character);
        compiler.compileAll();
    }

    public void setTarget(Character target) {
        this.target = target;
        compileAttributes(target);
    }

    public int getAttackResult() {
        return attackRoll.getResult();
    }

    public int getDamageResult() {
        int bonus = bonusDamageRoll == null ? 0 : bonusDamageRoll.getResult();
        return damageRoll.getResult() + bonus;
    }
    public void setMelee(boolean toggle) {
        isMelee = toggle;
    }

    private Character attacker;
    private Character target;
    private final Attribute damageAttr;
    private final Attribute attackAttr;
    private DiceRoll attackRoll;
    private DiceRoll damageRoll;
    private boolean isMelee;
    private DiceRoll bonusDamageRoll;

    public String getAttackRoll() {
        return attackRoll.toString();
    }

    public String getDamageRoll() {
        StringBuilder builder = new StringBuilder();
        builder.append(damageRoll.toString());
        if (bonusDamageRoll != null) {
            builder.append(" ");
            builder.append(bonusDamageRoll);
        }
        return builder.toString();
    }
}
