package com.ratrpg.events;

import com.ratrpg.Ratrpg;
import com.ratrpg.items.ItemManager;
import com.ratrpg.utilities.KnockBack;
import com.ratrpg.utilities.Message;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
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
            player.getWorld().createExplosion(player.getLocation(), 2f);      //player.getWorld(). HAS some neat stuff
            Message.send(player, "Boom.");
            return;
        }
        if (event.getItem().getItemMeta().equals(ItemManager.fireStaff.getItemMeta())) {
            tripleShot(event.getPlayer());
            return;
        }
    }

    //Cool heal: player.spawnParticle(Particle.TOTEM, loc, 10, 0, 0, 0);
    private static void fireshot(Player player) {
        new BukkitRunnable() {
            Vector dir = player.getLocation().getDirection().normalize();
            Location loc = player.getLocation();
            double t = 0;

            public void run() {
                t += 1.5; //Speed
                double x = dir.getX() * t;
                double y = dir.getY() * t + 1.5;
                double z = dir.getZ() * t;
                loc.add(x, y, z);
                player.spawnParticle(Particle.FIREWORKS_SPARK, loc, 0, 0, 0, 0);
//TODO take away that fact it can pen blocks
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
                if (t > 25) { //Lifetime
                    this.cancel();
                }
            }
        }.runTaskTimer(Ratrpg.getInstance(), 0, 1);
    }

    //TODO cooldown/mana
    private static void fireblast(Player player) {

        Fireball fireball = player.launchProjectile(Fireball.class);
        fireball.setBounce(false);
        fireball.setIsIncendiary(false);
        fireball.setYield(1.25f);
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

        player.spawnParticle(Particle.TOTEM, player.getEyeLocation(), 0, dir.getX() * 2D, dir.getY(), dir.getZ() * 2D);

        //hide arrow or particle deal dmg

        Message.send(player, "Floom!");
    }

    //https://www.youtube.com/c/SyntaxErrorYT/videos  channel
    //https://www.youtube.com/watch?v=3QQPNfcbeyk cooldowns
    //https://www.youtube.com/watch?v=YZfCBBvOMN4
    //https://www.youtube.com/watch?v=ZVGwlyHIzRA Particles

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

                //TODO remove getItemInHand, PlayerInventory
                if (shooter.getItemInUse().getItemMeta().equals(ItemManager.fireStaff.getItemMeta())) {
                    event.setDamage(10.0);
                }
            }
        }
    }

    //TODO I dont need this here, but keep for later
    /*@EventHandler
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
    }*/

    //TODO move to own event
    @EventHandler
    public void onFrameBrake(HangingBreakEvent event) {
        //TODO create a way for players to break, maybe item frame breaker

        //TODO make a debugBrakingStick
        if (event.getCause().toString().equals("ENTITY") || event.getCause().toString().equals("EXPLOSION")) {
            event.setCancelled(true);
        }
    }
}


/*//Left Click
if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
getLogger().info("Player left clicked a block.");
//With Element Axe?
if(event.getMaterial() == Material.IRON_AXE) {
if(element.get(player).equals(1)) {
player.sendMessage("ICE!");
}
if(element.get(player).equals(2)) {
player.sendMessage("FIRE");
}
if(element.get(player).equals(3)) {
Location spawningpoint = loc.add(0.0, 1.5, 0.0);
FallingBlock rock = w.spawnFallingBlock(spawningpoint, Material.GRASS, (byte) 0);
Vector direction = loc.getDirection();
Vector velocity = direction.multiply(2.0);
Arrow arrow = w.spawnArrow(spawningpoint, velocity, (float) 0.6, (float) 12);
arrow.setPassenger(rock);
arrow.setShooter(player);
rock.setDropItem(false);
}
}
}
}*/