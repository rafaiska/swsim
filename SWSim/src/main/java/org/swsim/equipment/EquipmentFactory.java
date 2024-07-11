package org.swsim.equipment;

import jdk.internal.dynalink.support.ClassMap;

import java.util.HashMap;

public class EquipmentFactory {
    private final HashMap<String, Class<? extends Equipment>> typeMap;
    private EquipmentSheet sheet;

    EquipmentFactory() {
        typeMap = new HashMap<>();
        typeMap.put("W", Weapon.class);
        typeMap.put("A", Armor.class);
    }

    public Equipment fromSheet(EquipmentSheet sheet) {
        this.sheet = sheet;
        Class<? extends Equipment> equipClass = typeMap.get(sheet.type);
        Equipment newEquip;
        try {
            return build(equipClass.newInstance());
        }
        catch (Exception e) {
            return null;
        }
    }

    private Equipment build(Equipment equipment) {
        throw new RuntimeException("Wrong equipment class");
    }

    private Weapon build(Weapon weapon) {
        weapon.setDamage(sheet.damage);
        weapon.setRange(sheet.range);
        return weapon;
    }
}
