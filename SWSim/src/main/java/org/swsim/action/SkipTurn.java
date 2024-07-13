package org.swsim.action;

import org.swsim.character.Character;

public class SkipTurn implements Action {
    private Character character;

    @Override
    public void execute() {}

    @Override
    public void setActor(Character character) {
        this.character = character;
    }
}
