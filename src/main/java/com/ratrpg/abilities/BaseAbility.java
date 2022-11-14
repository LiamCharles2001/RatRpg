package com.ratrpg.abilities;

import org.bukkit.entity.Player;

public abstract class BaseAbility {

    //TODO think about making a abiility managaer like itemManager
    private String name;
    private float cooldown;
    private String descption;
    private float manaCost;

    public BaseAbility() {

    }

    public abstract void useAbility(Player player);
    
    public void startCooldown(){

    }
}
