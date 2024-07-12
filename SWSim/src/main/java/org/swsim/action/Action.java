package org.swsim.action;

import org.swsim.character.Character;

public interface Action {
    void execute();
    void setActor(Character character);
}
