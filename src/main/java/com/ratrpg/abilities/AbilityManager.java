package com.ratrpg.abilities;

public class AbilityManager {
    public static TripleShot tripleShot;
    public static Metor metor;
    public static Fireshot fireshot;


    public static void initAbilities() {
        tripleShot = new TripleShot();
        metor = new Metor();
        fireshot = new Fireshot();
    }
}

