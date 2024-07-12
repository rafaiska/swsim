package org.swsim.equipment;

import org.junit.jupiter.api.Test;
import org.swsim.core.WorkspaceTest;

import static org.junit.jupiter.api.Assertions.*;

class EquipmentTest extends WorkspaceTest {

    @Test
    public void loadWeaponFromWorkspace() {
        EquipmentSheet equipmentSheet = getWorkspace().equipmentSheets.get("Unarmed");
        EquipmentLoader factory = new EquipmentLoader();
        Equipment equipment = factory.fromSheet(equipmentSheet);

        assertEquals(Weapon.class, equipment.getClass());
        Weapon weapon = (Weapon) equipment;
        assertEquals("Str", weapon.getDamageRoll());
        assertEquals("Fighting", weapon.getAttackRoll());
        assertEquals(0, weapon.getRange());
    }

}