package com.ratrpg.utilities;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

//This class is a shortcut from using the longer way of sending messages
public class Message {
    public static void send(CommandSender sender, String message) {
        send(sender, message, "&a");
    }

    public static void send(CommandSender sender, String message, String prefix) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + message));
    }

    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
