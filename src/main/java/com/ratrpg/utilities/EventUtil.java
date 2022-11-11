package com.ratrpg.utilities;

import com.ratrpg.Ratrpg;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class EventUtil {
    public static void register(Listener listener) {
        Bukkit.getServer().getPluginManager().registerEvents(listener, Ratrpg.getInstance());
    }
}
