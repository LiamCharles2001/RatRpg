package com.ratrpg.events;

import com.ratrpg.items.ItemManager;
import com.ratrpg.utilities.Message;
import org.bukkit.Particle;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
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
            player.getWorld().createExplosion(player.getLocation(), 2f);      //player.getWorld(). HAS some neat stuff
            Message.send(player, "Boom.");
            return;
        }

        if (event.getItem().getItemMeta().equals(ItemManager.fireStaff.getItemMeta())) {
            Player player = event.getPlayer();

            Vector dir = player.getLocation().getDirection();
            Vector vec = new Vector(dir.getX() * 2D, dir.getY(), dir.getZ() * 2D);

            Arrow arrow = event.getPlayer().getWorld().spawn(event.getPlayer().getEyeLocation(), Arrow.class);
            arrow.setDamage(5);
            arrow.setShooter(event.getPlayer());
            arrow.setVelocity(vec);

            Arrow arrow1 = event.getPlayer().getWorld().spawn(event.getPlayer().getEyeLocation(), Arrow.class);
            arrow1.setDamage(arrow.getDamage());
            arrow1.setShooter(event.getPlayer());
            arrow1.setVelocity(arrow.getVelocity().rotateAroundY(Math.toRadians(30)));

            Arrow arrow2 = event.getPlayer().getWorld().spawn(event.getPlayer().getEyeLocation(), Arrow.class);
            arrow2.setDamage(arrow.getDamage());
            arrow2.setShooter(event.getPlayer());
            arrow2.setVelocity(arrow.getVelocity().rotateAroundY(Math.toRadians(-30)));

            player.spawnParticle(Particle.TOTEM, player.getEyeLocation(), 0, dir.getX() * 2D, dir.getY(), dir.getZ() * 2D);

            //hide arrow or particle deal dmg

            Message.send(player, "Floom!");
            return;
        }
    }

    //https://www.youtube.com/c/SyntaxErrorYT/videos  channel
    //https://www.youtube.com/watch?v=3QQPNfcbeyk cooldowns
    //https://www.youtube.com/watch?v=ZVGwlyHIzRA Particles

    @EventHandler
    public void onShoot(EntityShootBowEvent event) {

        if (!(event.getProjectile() instanceof Arrow)) {
            return;
        }

        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        if (event.getBow() == null) {
            return;
        }

        if (event.getBow().getItemMeta() == null) {
            return;
        }

        if (event.getBow().getItemMeta().getLore() == null) {
            return;
        }

        if (event.getBow().getItemMeta().equals(ItemManager.fireStaff.getItemMeta())) {

            Arrow arrow = (Arrow) event.getProjectile();

            Arrow arrow1 = event.getEntity().getWorld().spawn(event.getEntity().getEyeLocation(), Arrow.class);
            arrow1.setDamage(arrow.getDamage());
            arrow1.setShooter(event.getEntity());
            arrow1.setVelocity(arrow.getVelocity().rotateAroundY(Math.toRadians(30)));

            Arrow arrow2 = event.getEntity().getWorld().spawn(event.getEntity().getEyeLocation(), Arrow.class);
            arrow2.setDamage(arrow.getDamage());
            arrow2.setShooter(event.getEntity());
            arrow2.setVelocity(arrow.getVelocity().rotateAroundY(Math.toRadians(-30)));
        }
    }
}
