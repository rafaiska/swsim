package org.swsim.character;

import org.swsim.equipment.EquipmentSheet;

import java.io.Serializable;
import java.util.HashMap;

public class CharacterSheet implements Serializable {
    public String name;
    public Boolean isUnique;
    public Boolean isWildCard;
    public Integer advancements;
    public HashMap<String, String> attributes;
    public EquipmentSheet equippedBothHands;
    public EquipmentSheet equippedOffHand;
    public EquipmentSheet equippedMainHand;
}
