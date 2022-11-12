package com.ratrpg.commands;

import com.ratrpg.items.ItemManager;
import com.ratrpg.utilities.CommandBase;
import com.ratrpg.utilities.ItemCreator;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Sword {
    public Sword() {
        new CommandBase("sword", "Gives you a sword", true)
        {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;

                ItemCreator item = new ItemCreator(Material.DIAMOND_SWORD);
                item.setName("&aHellow World");
                item.addLore("&cTest");
                item.setUnbreakable(true);

                player.getInventory().addItem(item.getItemStack());

                return true;
            }

            @Override
            public String getUsage() {
                return "Sword takes no arguments";
            }
        };

        new CommandBase("ssword", "Gives you a sword", true)
        {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;

                player.getInventory().addItem(ItemManager.wand);
                player.getInventory().addItem(ItemManager.fireStaff);

                return true;
            }

            @Override
            public String getUsage() {
                return "Sword takes no arguments";
            }
        };
    }
}
