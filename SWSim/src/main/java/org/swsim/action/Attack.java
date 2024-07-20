package org.swsim.action;

import org.swsim.attribute.Attribute;
import org.swsim.attribute.AttributeCompiler;
import org.swsim.attribute.AttributeRoller;
import org.swsim.character.Character;
import org.swsim.core.DiceRoll;

public class Attack implements Action {
    public Attack(Attribute attack, Attribute damage) {
        isMelee = true;
        this.damageAttr = damage;
        this.attackAttr = attack;
    }

    @Override
    public void execute() {
        AttributeRoller roller = new AttributeRoller(attackAttr);
        attackRoll = roller.roll();
        Attribute damageBonus = wasRaised(roller) ? new Attribute("d6") : new Attribute("0");
        roller = new AttributeRoller(damageAttr).addBonus(damageBonus);
        damageRoll = roller.roll();
    }

    public boolean wasRaised(AttributeRoller roller) {
        int defense = isMelee ? target.getAttribute("Parry").castToInt() : 4;
        int raises = roller.getRaisesAgainst(defense);
        return raises >= 1;
    }

    @Override
    public void setActor(Character character) {
        attacker = character;
        compileAttributes();
    }

    private void compileAttributes() {
        AttributeCompiler compiler = new AttributeCompiler(attacker);
        compiler.compileAll();
        compiler.compile(attackAttr);
        compiler.compile(damageAttr);
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
}
