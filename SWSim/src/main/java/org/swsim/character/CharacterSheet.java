package org.swsim.character;

import java.io.Serializable;
import java.util.HashMap;

public class CharacterSheet implements Serializable {
    public String name;
    public Boolean isUnique;
    public Boolean isWildCard;
    public Integer advancements;
    public HashMap<String, String> attributes;
    public HashMap<String, String> skills;
}
