package org.swsim.equipment;

import org.swsim.attribute.Attribute;

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
        weapon.setDamage(new Attribute(sheet.damageRoll));
        weapon.setAttackRoll(new Attribute(sheet.attackRoll));
        weapon.setRange(new Attribute(String.valueOf(sheet.range)));
        return weapon;
    }
}
