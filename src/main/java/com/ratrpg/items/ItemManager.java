package com.ratrpg.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    public static ItemStack wand;
    public static ItemStack fireStaff;

    public static void initItems() {
        createWand();
        createFireStaff();
    }

    private static void createWand() {
        ItemStack item = new ItemStack(Material.STICK, 1);  //Item
        ItemMeta meta = item.getItemMeta();                        //Item Data
        meta.setDisplayName("Stick of Power");

        meta.addEnchant(Enchantment.LUCK, 1, false);

        List<String> lores = new ArrayList<>();                    //Item Text
        lores.add("This is sus");
        meta.setLore(lores);

        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        item.setItemMeta(meta);
        wand = item;
    }

    private static void createFireStaff()
    {
        ItemStack item = new ItemStack(Material.IRON_HOE, 1);  //Item
        ItemMeta meta = item.getItemMeta();                        //Item Data
        meta.setDisplayName("Fire Staff");
        meta.isUnbreakable();

        meta.addEnchant(Enchantment.LUCK, 1, false);

        List<String> lores = new ArrayList<>();                    //Item Text
        lores.add("Right click to cast fire ball");
        lores.add("Item Ability: Fireball");
        meta.setLore(lores);

        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        item.setItemMeta(meta);
        fireStaff = item;
    }
}
