package com.ratrpg.commands;

import com.ratrpg.Ratrpg;
import com.ratrpg.utilities.Message;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Spawn {

    private Location spawn = null;
    public Spawn(Ratrpg plugin) {
        FileConfiguration config = plugin.getConfig();

        String worldName = config.getString("spawn.world");
        if(worldName == null) {
            Bukkit.getLogger().warning("spawn.world does not exist within config.yml");
            return;
        }

        World world = Bukkit.getWorld(worldName);
        if(world == null) {
            Bukkit.getLogger().severe("World \"" + worldName + "\" does not exist");
            return;
        }

        int x = config.getInt("spawn.x");
        int y = config.getInt("spawn.y");
        int z = config.getInt("spawn.z");
        float yaw = (float) config.getDouble("spawn.yaw");
        float pitch = (float) config.getDouble("spawn.pitch");

        spawn = new Location(world, x, y, z, yaw, pitch);

        new CommandBase("setSpawn", "Set spawn location from your current location.",true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;
                Location location = player.getLocation();

                config.set("spawn.world", location.getWorld().getName());
                config.set("spawn.x", location.getBlockX());
                config.set("spawn.y", location.getBlockY());
                config.set("spawn.z", location.getBlockZ());
                config.set("spawn.yaw", location.getYaw());
                config.set("spawn.pitch", location.getPitch());

                plugin.saveConfig();
                spawn = location;

                Message.send(player, "New spawn has been set.");
                return true;

            }

            @Override
            public String getUsage() {
                return "Set spawn takes no arguments.";
            }
        }.enableDelay(2).setPermission("spawn.set");

        new CommandBase("spawn", "Teleports you to spawn.",true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;
                player.teleport(spawn);
                Message.send(player, "Teleporting..");
                return true;
            }

            @Override
            public String getUsage() {
                return "Spawn takes no arguments.";
            }
        }.enableDelay(2);
    }
}
