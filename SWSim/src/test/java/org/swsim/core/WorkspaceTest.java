package org.swsim.core;

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
    }

    private void createEquipment() {
        workspace.equipmentSheets = new HashMap<>();

        EquipmentSheet unarmed = new EquipmentSheet();
        unarmed.canEquip = true;
        unarmed.range = 0;
        unarmed.damage = "Str";
        unarmed.type = "W";
        workspace.equipmentSheets.put("Unarmed", unarmed);
    }
}