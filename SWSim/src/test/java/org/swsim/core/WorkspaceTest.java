package org.swsim.core;

import org.swsim.character.CharacterSheet;
import org.swsim.equipment.EquipmentSheet;

import java.util.HashMap;

public class WorkspaceTest {
    Workspace workspace;

    public Workspace getWorkspace() {
        return workspace;
    }

    public WorkspaceTest() {
        workspace = new Workspace();
        createEquipment();
        createCharacters();
    }

    private void createCharacters() {
        workspace.characterSheets = new HashMap<>();

        CharacterSheet passoca = new CharacterSheet();
        passoca.isUnique = true;
        passoca.isWildCard = true;
        passoca.name = "Passoca";
        passoca.attributes = new HashMap<>();
        passoca.attributes.put("Agi", "d6");
        passoca.attributes.put("Sma", "d6");
        passoca.attributes.put("Spi", "d6");
        passoca.attributes.put("Str", "d6");
        passoca.attributes.put("Vig", "d6");
        passoca.attributes.put("Fighting", "d10");
        passoca.attributes.put("Pace", "6");
        passoca.attributes.put("Parry", "7");
        passoca.attributes.put("Toughness", "5");
        passoca.equippedBothHands = workspace.equipmentSheets.get("Unarmed");
        passoca.advancements = 0;

        CharacterSheet grunto = new CharacterSheet();
        grunto.isUnique = false;
        grunto.isWildCard = false;
        grunto.name = "Grunto";
        grunto.attributes = new HashMap<>();
        grunto.attributes.put("Agi", "d4");
        grunto.attributes.put("Sma", "d4");
        grunto.attributes.put("Spi", "d4");
        grunto.attributes.put("Str", "d4");
        grunto.attributes.put("Vig", "d4");
        grunto.attributes.put("Fighting", "d6");
        grunto.attributes.put("Pace", "6");
        grunto.attributes.put("Parry", "5");
        grunto.attributes.put("Toughness", "4");
        grunto.equippedBothHands = workspace.equipmentSheets.get("Unarmed");
        grunto.advancements = 0;
    }

    private void createEquipment() {
        workspace.equipmentSheets = new HashMap<>();

        EquipmentSheet unarmed = new EquipmentSheet();
        unarmed.canEquip = true;
        unarmed.range = 0;
        unarmed.damageRoll = "Str";
        unarmed.attackRoll = "Fighting";
        unarmed.type = "W";
        workspace.equipmentSheets.put("Unarmed", unarmed);
    }
}