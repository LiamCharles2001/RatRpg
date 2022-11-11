package com.ratrpg.events;


import com.ratrpg.utilities.PlayerUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static com.ratrpg.utilities.PlayerUtil.loadPlayerData;
import static com.ratrpg.utilities.PlayerUtil.savePlayerData;

public class PlayerJoin implements Listener {

    @EventHandler
    private void onJoin(PlayerJoinEvent event) {
        loadPlayerData(event.getPlayer());
    }

    @EventHandler
    private void onQuit(PlayerQuitEvent event) {

        //TODO, create auto save and a server shutdown save, mainly check on when this event fires
        savePlayerData(event.getPlayer());

        //Important
        PlayerUtil.setPlayerMemory(event.getPlayer(), null);
    }
}