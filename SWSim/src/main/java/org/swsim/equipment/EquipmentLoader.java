package org.swsim.equipment;

import java.util.HashMap;

public class EquipmentLoader {
    public Equipment fromSheet(EquipmentSheet sheet) {
        switch (sheet.type) {
            case "W": return buildWeapon(sheet);
            case "A": return buildArmor(sheet);
            default: throw new RuntimeException("Wrong equipment type");
        }
    }

    private Armor buildArmor(EquipmentSheet sheet) {
        Armor armor = new Armor();
        return armor;
    }

    private Weapon buildWeapon(EquipmentSheet sheet) {
        Weapon weapon = new Weapon();
        weapon.setDamage(sheet.damageRoll);
        weapon.setAttackRoll(sheet.attackRoll);
        weapon.setRange(sheet.range);
        return weapon;
    }
}
