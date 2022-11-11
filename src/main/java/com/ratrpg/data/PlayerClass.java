package com.ratrpg.data;

import com.ratrpg.utilities.PlayerUtil;
import org.bukkit.entity.Player;

public enum PlayerClass {
    NONE(0),
    MAGE(1),
    WARRIOR(2),
    RANGER(3),
    ROGUE(4),
    PALADIN(5);
    private final int value;

    private PlayerClass(int value) {
        this.value = value;
    }

    public int getIntValue() {
        return value;
    }

    public static PlayerClass getClassByInt(int value) {
        switch (value) {
            case 0:
                return NONE;
            case 1:
                return MAGE;
            case 2:
                return WARRIOR;
            case 3:
                return RANGER;
            case 4:
                return ROGUE;
            case 5:
                return PALADIN;
        }
        return null;
    }

    public static String getClassName(PlayerClass playerClass) {
        switch (playerClass.getIntValue()) {
            case 0:
                return "None";
            case 1:
                return "Mage";
            case 2:
                return "Warrior";
            case 3:
                return "Ranger";
            case 4:
                return "Rogue";
            case 5:
                return "Paladin";
        }
        return "NOTHING FOUND";
    }
}
