package com.ratrpg.events;

import com.ratrpg.Ratrpg;
import com.ratrpg.abilities.AbilityManager;
import com.ratrpg.items.ItemManager;
import com.ratrpg.utilities.KnockBack;
import com.ratrpg.utilities.Message;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class PlayerInteract implements Listener {

    @EventHandler
    public static void onRightClick(PlayerInteractEvent event) {

        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        if (event.getItem() == null) {
            return;
        }

        if (event.getItem().getItemMeta().equals(ItemManager.wand.getItemMeta())) {
            Player player = event.getPlayer();
            player.getWorld().createExplosion(player.getLocation(), 2f);
            Message.send(player, "Boom.");
            return;
        }
        if (event.getItem().getItemMeta().equals(ItemManager.fireStaff.getItemMeta())) {
            //tripleShot(event.getPlayer());
            AbilityManager.tripleShot.useAbility(event.getPlayer());
            return;
        }
    }

    @EventHandler
    public void onFrameBrake(HangingBreakEvent event) {

        if (event.getCause().toString().equals("ENTITY") || event.getCause().toString().equals("EXPLOSION")) {
            event.setCancelled(true);
        }
    }
}
