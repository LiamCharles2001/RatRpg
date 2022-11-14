package com.ratrpg.abilities;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class TripleShot extends AbilityBase {

    public TripleShot(){
        this.name = "Tripleshot";
    }

    @Override
    public void useAbility(Player player) {
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
    }
}
