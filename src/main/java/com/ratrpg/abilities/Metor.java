package com.ratrpg.abilities;

import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;

public class Metor extends AbilityBase{
    public Metor(){
        this.name = "Fireblast";
    }
    @Override
    public void useAbility(Player player) {
        Fireball fireball = player.launchProjectile(Fireball.class);
        fireball.setBounce(false);
        fireball.setIsIncendiary(false);
        fireball.setYield(1.25f);
    }
}

    /*@EventHandler
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
    }*/
