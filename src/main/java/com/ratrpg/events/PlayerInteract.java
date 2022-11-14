package com.ratrpg.events;

import com.ratrpg.Ratrpg;
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
            tripleShot(event.getPlayer());
            return;
        }
    }

    private static void fireshot(Player player) {
        new BukkitRunnable() {
            Vector dir = player.getLocation().getDirection().normalize();
            Location loc = player.getLocation();
            double tick = 0;

            public void run() {
                tick += 1.5; //Speed
                double x = dir.getX() * tick;
                double y = dir.getY() * tick + 1.5;
                double z = dir.getZ() * tick;
                loc.add(x, y, z);
                player.spawnParticle(Particle.FIREWORKS_SPARK, loc, 0, 0, 0, 0);
                //TODO take away that fact it can pen blocks

                //TODO Test this
                Message.send(player, "1: "+loc.getBlock().toString() );
                Message.send(player, "2: "+loc.getWorld().getBlockAt(loc).getType().toString() );
                if(loc.getWorld().getBlockAt(loc).getType() != Material.AIR) {
                    Message.send(player, "3.");
                    this.cancel();
                }

                for (Entity e : loc.getChunk().getEntities()) {
                    if (e.getLocation().distance(loc) < 1.9) { //Hitbox
                        if (!e.equals(player)) {
                            if (e instanceof LivingEntity) {
                                //e.setFireTicks(20 * 5); //5 secs
                                ((LivingEntity) e).damage(2);
                                KnockBack.applyKnockback(((LivingEntity) e), player, 0);
                            }
                        }
                    }
                }

                loc.subtract(x, y, z);
                if (tick > 25) { //Lifetime
                    this.cancel();
                }
            }
        }.runTaskTimer(Ratrpg.getInstance(), 0, 1);
    }

    private static void fireblast(Player player) {

        Fireball fireball = player.launchProjectile(Fireball.class);
        fireball.setBounce(false);
        fireball.setIsIncendiary(false);
        fireball.setYield(1.25f);
        //fireball.setTicksLived(); //TODO Test this
        //fireball.addPassenger(player);
    }

    private static void tripleShot(Player player) {

        Vector dir = player.getLocation().getDirection();
        Vector vec = new Vector(dir.getX() * 2D, dir.getY(), dir.getZ() * 2D);

        Arrow arrow = player.getWorld().spawn(player.getEyeLocation(), Arrow.class);
        arrow.setDamage(5);
        arrow.setShooter(player);
        arrow.setVelocity(vec);

        Arrow arrow1 = player.getWorld().spawn(player.getEyeLocation(), Arrow.class);
        arrow1.setDamage(arrow.getDamage());
        arrow1.setShooter(player);
        arrow1.setVelocity(arrow.getVelocity().rotateAroundY(Math.toRadians(30)));

        Arrow arrow2 = player.getWorld().spawn(player.getEyeLocation(), Arrow.class);
        arrow2.setDamage(arrow.getDamage());
        arrow2.setShooter(player);
        arrow2.setVelocity(arrow.getVelocity().rotateAroundY(Math.toRadians(-30)));

        //hide arrow or particle deal dmg

        Message.send(player, "Floom!");
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Fireball) {
            Fireball fireball = (Fireball) event.getDamager();
            if (fireball.getShooter() instanceof Player) {
                Player shooter = (Player) fireball.getShooter();

                //Stops self damage
                if (event.getEntity() == shooter) {
                    event.setCancelled(true);
                }

                if (shooter.getItemInUse().getItemMeta().equals(ItemManager.fireStaff.getItemMeta())) {
                    event.setDamage(10.0);
                }
            }
        }
    }


    //TODO move to own event
    @EventHandler
    public void onFrameBrake(HangingBreakEvent event) {

        if (event.getCause().toString().equals("ENTITY") || event.getCause().toString().equals("EXPLOSION")) {
            event.setCancelled(true);
        }
    }
}
