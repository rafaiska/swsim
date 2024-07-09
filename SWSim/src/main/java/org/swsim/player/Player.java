package org.swsim.player;

import org.swsim.action.Action;
import org.swsim.character.Character;

import java.util.List;

public abstract class Player {
    abstract public Action play(Character character, List<Character> charactersInCombat);
}
