package org.swsim.action;

import org.swsim.character.Character;

public class TakeDamage implements Action {
    private final Attack attack;
    private Character character;

    public TakeDamage(Attack attack) {
        this.attack = attack;
    }

    @Override
    public void execute() {
            // TODO: check if aced, get damage and bonuses, try to soak, check if wounded
    }

    @Override
    public void setActor(Character character) {
        this.character = character;
    }
}
