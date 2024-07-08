package org.swsim.character;

import java.io.Serializable;
import java.util.HashMap;

public class CharacterSheet implements Serializable {
    String name;
    Boolean isTemplate;
    Integer advancements;
    HashMap<String, Integer> attributes;
    HashMap<String, Integer> skills;
}
