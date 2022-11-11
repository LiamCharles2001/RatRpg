package com.ratrpg.data;

public class PlayerMemory {
    private int selectedProfileId;
    private PlayerClass playerClass;
    private double maxHealth;
    private double health;
    private double armor;
    private double maxMana;
    private double mana;
    private static final int maxLevel = 50;
    private int level;
    private int requiredExperience;
    private int experience;
    private int intelligent;
    private int strength;
    private int agility;

    public void damage(double amount) {
        health -= amount;
        if (health <= 0) {
            death();
        }
    }

    public void heal(double amount) {
        if ((health + amount) > maxHealth) {
            health = maxHealth;
        } else {
            health += amount;
        }
    }

    public static int determineXPForNextLevel(int playerLevel) {
        playerLevel += 1;
        int levels = maxLevel;                //<-- Max level
        float xpLevel1 = 500.0f;        //<-- xp needed for first level
        float xpLevel50 = 400000.0f;    //<-- xp needed for last level
        float temp1 = (float) Math.log(xpLevel50 / xpLevel1);
        float b = temp1 / (levels - 1);
        float temp2 = (float) (Math.exp(b) - 1);
        float a = (xpLevel1) / temp2;
        int oldxp = (int) (a * Math.exp((float) b * (playerLevel - 1)));
        int newxp = (int) (a * Math.exp((float) b * playerLevel));
        int temp = newxp - oldxp;
        return temp = (int) Math.round((float) temp / 10.0f) * 10;
    }

    public void giveExperience(int amount) {
        experience += amount;

        if (experience >= requiredExperience) {
            levelUp();
        }
    }

    private void levelUp() {

        experience = 0;

        if (level < maxLevel) {
            level++;
        }

        requiredExperience = determineXPForNextLevel(level);
    }

    public void death() {
        //kill player
    }

    public void respawn() {

    }

    public static int getMaxLevel() {
        return maxLevel;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getArmor() {
        return armor;
    }

    public void setArmor(double armor) {
        this.armor = armor;
    }

    public PlayerClass getPlayerClass() {
        return playerClass;
    }

    public void setPlayerClass(PlayerClass playerClass) {
        this.playerClass = playerClass;
    }

    public double getMana() {
        return mana;
    }

    public void setMana(double mana) {
        this.mana = mana;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getIntelligent() {
        return intelligent;
    }

    public void setIntelligent(int intelligent) {
        this.intelligent = intelligent;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public int getSelectedProfileId() {
        return selectedProfileId;
    }

    public void setSelectedProfileId(int selectedProfileId) {
        this.selectedProfileId = selectedProfileId;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    public double getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(double maxMana) {
        this.maxMana = maxMana;
    }

    public int getRequiredExperience() {
        return requiredExperience;
    }

    public void setRequiredExperience(int requiredExperience) {
        this.requiredExperience = requiredExperience;
    }
}
