package org.swsim.action;

import org.swsim.character.Character;

public class Attack extends Action {
    public Attack(String attribute, String skill) {
        this.attributeForDamageRoll = attribute;
        this.skillForAttackRoll = skill;
    }

    @Override
    void execute() {
    }

    public void setAttacker(Character attacker) {
        this.attacker = attacker;
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

    public String getAttributeForDamageRoll() {
        return attributeForDamageRoll;
    }

    public void setAttributeForDamageRoll(String attributeForDamageRoll) {
        this.attributeForDamageRoll = attributeForDamageRoll;
    }

    public String getSkillForAttackRoll() {
        return skillForAttackRoll;
    }

    public void setSkillForAttackRoll(String skillForAttackRoll) {
        this.skillForAttackRoll = skillForAttackRoll;
    }

    private Character attacker;
    private Character target;
    private String attributeForDamageRoll;
    private String skillForAttackRoll;
}
