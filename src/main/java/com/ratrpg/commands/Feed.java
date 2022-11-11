package com.ratrpg.commands;

import com.ratrpg.utilities.CommandBase;
import com.ratrpg.utilities.Message;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Feed {
    public Feed() {
        new CommandBase("feed", "Feeds player",true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;
                player.setFoodLevel(20);
                Message.send(player, "You have been fed.");
                return true;
            }

            @Override
            public String getUsage() {
                return "This Feeds you";
            }
        }.enableDelay(2);
    }
}
