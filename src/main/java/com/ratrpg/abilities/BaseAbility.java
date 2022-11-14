package com.ratrpg.abilities;

import org.bukkit.entity.Player;

public abstract class BaseAbility {

    //TODO think about making a abiility managaer like itemManager
    protected String name;
    protected float cooldown;
    protected String descption;
    protected float manaCost;

    public BaseAbility() {

    }

    public abstract void createAbility();
    public abstract void useAbility(Player player);

    public void startCooldown(){

    }
}

//Ability
//Holds the data of the ability along with the usage

//Ability Manager
// holds a static version of the ability so it can be used anywhere