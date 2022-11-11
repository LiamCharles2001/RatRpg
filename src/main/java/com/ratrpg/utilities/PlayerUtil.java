package com.ratrpg.utilities;

import com.ratrpg.Ratrpg;
import com.ratrpg.data.PlayerClass;
import com.ratrpg.data.PlayerMemory;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//This class handles saving/loading for a player, along with holding the player's data in memory.
public class PlayerUtil {
    private static final Map<String, PlayerMemory> playerMemory = new HashMap<>();
    public static PlayerMemory getPlayerMemory(Player player) {
        if(!playerMemory.containsKey(player.getUniqueId().toString())) {
            PlayerMemory playerToAdd = new PlayerMemory();
            playerMemory.put(player.getUniqueId().toString(), playerToAdd);
            return playerToAdd;
        }
        return playerMemory.get(player.getUniqueId().toString());
    }

    public static void setPlayerMemory(Player player, PlayerMemory memory) {
        if(memory == null) {
            playerMemory.remove(player.getUniqueId().toString());
        } else if (playerMemory.containsKey(player.getUniqueId().toString())) {
            playerMemory.replace(player.getUniqueId().toString(), memory);
        }
        else {
            playerMemory.put(player.getUniqueId().toString(), memory);
        }
    }

    public static String getFolderPath(Player player) {
        return Ratrpg.getInstance().getDataFolder().getAbsolutePath() + "/player/" + player.getUniqueId();
    }

    //Saves player data into their player data file
    public static void savePlayerData(Player player) {
        PlayerMemory memory = PlayerUtil.getPlayerMemory(player);
        File file = new File(PlayerUtil.getFolderPath(player) + "/general.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        int selectedProfileId = memory.getSelectedProfileId();
        config.set("selectedProfile", selectedProfileId);

        config.set("profile."+selectedProfileId+".stats.maxHealth", memory.getMaxHealth());
        config.set("profile."+selectedProfileId+".stats.health", memory.getHealth());
        config.set("profile."+selectedProfileId+".stats.armor", memory.getArmor());
        config.set("profile."+selectedProfileId+".stats.maxMana", memory.getMaxMana());
        config.set("profile."+selectedProfileId+".stats.mana", memory.getMana());
        config.set("profile."+selectedProfileId+".stats.level", memory.getLevel());
        config.set("profile."+selectedProfileId+".stats.experience", memory.getExperience());
        config.set("profile."+selectedProfileId+".stats.requiredExperience", memory.getRequiredExperience());

        config.set("profile."+selectedProfileId+".stats.playerClass", memory.getPlayerClass().getIntValue());

        config.set("profile."+selectedProfileId+".stats.agility", memory.getAgility());
        config.set("profile."+selectedProfileId+".stats.strength", memory.getStrength());
        config.set("profile."+selectedProfileId+".stats.intelligent", memory.getIntelligent());

        try{
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Loads player data into memory
    public static void loadPlayerData(Player player) {
        PlayerMemory memory = new PlayerMemory();
        File file = new File(PlayerUtil.getFolderPath(player) + "/general.yml");

        if(file.exists()){ //The player has a config file and the profile created
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);

            int selectedProfileId = config.getInt("selectedProfile");
            memory.setSelectedProfileId(selectedProfileId);

            memory.setMaxHealth(config.getDouble("profile."+selectedProfileId+".stats.maxHealth"));
            memory.setHealth(config.getDouble("profile."+selectedProfileId+".stats.health"));
            memory.setArmor(config.getDouble("profile."+selectedProfileId+".stats.armor"));
            memory.setMaxMana(config.getDouble("profile."+selectedProfileId+".stats.maxMana"));
            memory.setMana(config.getDouble("profile."+selectedProfileId+".stats.mana"));
            memory.setLevel(config.getInt("profile."+selectedProfileId+".stats.level"));
            memory.setExperience(config.getInt("profile."+selectedProfileId+".stats.experience"));
            memory.setRequiredExperience(config.getInt("profile."+selectedProfileId+".stats.requiredExperience"));

            memory.setPlayerClass(PlayerClass.getClassByInt(config.getInt("profile." + selectedProfileId + ".stats.playerClass")));

            memory.setAgility(config.getInt("profile."+selectedProfileId+".stats.agility"));
            memory.setStrength(config.getInt("profile."+selectedProfileId+".stats.strength"));
            memory.setIntelligent(config.getInt("profile."+selectedProfileId+".stats.intelligent"));

        } else {
            newProfileDefault(memory, 1);
        }

        PlayerUtil.setPlayerMemory(player, memory);
    }

    //Loads another player's profile using their profileID, remember to save any player data before using this.
    public static void switchPlayerData(Player player, int profileId) {
        PlayerMemory memory = new PlayerMemory();
        File file = new File(PlayerUtil.getFolderPath(player) + "/general.yml");

        if(file.exists()) { //The player has a config file
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);

            memory.setSelectedProfileId(profileId);

            memory.setMaxHealth(config.getDouble("profile."+profileId+".stats.maxHealth"));
            memory.setHealth(config.getDouble("profile."+profileId+".stats.health"));
            memory.setArmor(config.getDouble("profile."+profileId+".stats.armor"));
            memory.setMaxMana(config.getDouble("profile."+profileId+".stats.maxMana"));
            memory.setMana(config.getDouble("profile."+profileId+".stats.mana"));
            memory.setLevel(config.getInt("profile."+profileId+".stats.level"));
            memory.setExperience(config.getInt("profile."+profileId+".stats.experience"));
            memory.setRequiredExperience(config.getInt("profile."+profileId+".stats.requiredExperience"));

            memory.setPlayerClass(PlayerClass.getClassByInt(config.getInt("profile." + profileId + ".stats.playerClass")));

            memory.setAgility(config.getInt("profile."+profileId+".stats.agility"));
            memory.setStrength(config.getInt("profile."+profileId+".stats.strength"));
            memory.setIntelligent(config.getInt("profile."+profileId+".stats.intelligent"));

            //If Profile exists but is making a new profile
            if(config.getString("profile."+profileId) == null) {
                newProfileDefault(memory, profileId);
            }
        } else {
            newProfileDefault(memory, 1);
        }

        PlayerUtil.setPlayerMemory(player, memory);
    }

    //Sets the default values for any new player or profile //TODO create an overload of memory to be just player //TODO rename to just newProfile?
    public static void newProfileDefault(PlayerMemory memory, int profileId)
    {
        memory.setMaxHealth(100);
        memory.setHealth(100);
        memory.setMaxMana(100);
        memory.setMana(100);
        memory.setArmor(0);
        memory.setLevel(1);
        memory.setExperience(0);
        memory.setRequiredExperience(100);
        memory.setSelectedProfileId(profileId);

        memory.setPlayerClass(PlayerClass.NONE);

        memory.setAgility(0);
        memory.setStrength(0);
        memory.setIntelligent(0);
    }

    public static boolean hasNonePlayerClass(Player player) {
        PlayerMemory memory = PlayerUtil.getPlayerMemory(player);

        if (memory.getPlayerClass() == PlayerClass.NONE) {
            return true;
        }
        return false;
    }

    public static void switchClass(Player player, PlayerClass playerClass) {
        PlayerMemory memory = PlayerUtil.getPlayerMemory(player);
        memory.setPlayerClass(playerClass);
    }
}

