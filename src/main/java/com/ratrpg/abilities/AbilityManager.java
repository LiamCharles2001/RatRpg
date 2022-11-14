package com.ratrpg.abilities;

public class AbilityManager {
    public static TripleShot tripleShot;
    public static Fireblast fireblast;
    public static Fireshot fireshot;


    public static void initAbilities() {
        tripleShot = new TripleShot();
        fireblast = new Fireblast();
        fireshot = new Fireshot();
    }
}

