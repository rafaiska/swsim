package org.swsim.core;

import org.swsim.character.CharacterSheet;
import org.swsim.equipment.EquipmentSheet;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class Workspace implements Serializable {
    public HashMap<String, CharacterSheet> characterSheets;
    public HashMap<String, EquipmentSheet> equipmentSheets;
}
