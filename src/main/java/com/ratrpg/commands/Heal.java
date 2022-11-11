package com.ratrpg.commands;

import com.ratrpg.utilities.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Heal implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player))
        {
            //Question: What color is this and why am I doing &c
            //Message.send(commandSender, "&cOnly players can use this command");
            Message.send(commandSender, "&cOnly players can use this command");
            return true;
        }

        Player player = (Player) commandSender;
        player.setHealth(20.0d);

        commandSender.sendMessage("Healed");
        return true;
    }
}
