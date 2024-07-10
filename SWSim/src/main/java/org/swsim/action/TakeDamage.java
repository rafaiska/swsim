package org.swsim.action;

public class TakeDamage extends Action {
    private final Attack attack;

    public TakeDamage(Attack attack) {
        this.attack = attack;
    }

    @Override
    public void execute() {
            // TODO: check if aced, get damage and bonuses, try to soak, check if wounded
    }
}
