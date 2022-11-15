package com.ratrpg.abilities;

import com.ratrpg.Ratrpg;
import com.ratrpg.utilities.KnockBack;
import com.ratrpg.utilities.Message;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class Fireshot extends AbilityBase{

    public Fireshot(){
        this.name = "Fireshot";
    }
    @Override
    public void useAbility(Player player) {
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

                //Message.send(player, "2: "+loc.getWorld().getBlockAt(loc).getType().toString() );
                if(loc.getWorld().getBlockAt(loc).getType() != Material.AIR) {
                    this.cancel();
                    return;
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
}
