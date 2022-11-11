package com.ratrpg;

import com.ratrpg.commands.*;
import com.ratrpg.events.ExperienceChange;
import com.ratrpg.events.PlayerJoin;
import com.ratrpg.menus.ClassSelector;
import com.ratrpg.menus.Menu;
import com.ratrpg.menus.Profile;
import com.ratrpg.menus.ProfileSelector;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Ratrpg extends JavaPlugin {

    private static Ratrpg instance;

    public static Ratrpg getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {

        try {
            instance = this;

            System.out.println("Rat RPG has been enable");

            //Uses the config.yml as the default config file
            saveDefaultConfig();

            //Registers all events
            //Bukkit.getPluginManager().registerEvents(new HelloWorld(), this);
            Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
            Bukkit.getPluginManager().registerEvents(new ExperienceChange(), this);

            //Registers all commands
            //getCommand("heal").setExecutor(new Heal());
            new Feed();
            new Spawn(this);
            new Sword();
            new Menu(this);
            new ProfileSelector(this);
            new Profile(this);
            new Experience();
            new ClassSelector(this);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        System.out.println("Rat RPG has been disable");
    }
}
