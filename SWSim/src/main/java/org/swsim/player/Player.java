package org.swsim.player;

import org.swsim.character.Character;

import java.util.List;

public abstract class Player {
    abstract public void play(Character character, List<Character> charactersInCombat);
}
