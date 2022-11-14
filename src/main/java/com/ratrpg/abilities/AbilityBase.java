package com.ratrpg.abilities;

import org.bukkit.entity.Player;

public abstract class AbilityBase {

    protected String name;
    protected float cooldown; //VIDEO
    protected String description;
    protected double manaCost;
    //Getters / Setters
    public abstract void useAbility(Player player);

    public void startCooldown() {

    }
}