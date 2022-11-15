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
    public static ItemStack metorStaff;
    public static ItemStack magicBow;

    public static void initItems() {
        createWand();
        createFireStaff();
        createMetorStaff();
        createMagicBow();
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

    private static void createFireStaff() {
        ItemStack item = new ItemStack(Material.BLAZE_ROD, 1);  //Item
        ItemMeta meta = item.getItemMeta();                        //Item Data
        meta.setDisplayName("Fire Staff");
        meta.isUnbreakable();

        meta.addEnchant(Enchantment.LUCK, 1, false);

        List<String> lores = new ArrayList<>();                    //Item Text
        lores.add("Right click to cast Fireshot");
        lores.add("Item Ability: Fireshot");
        meta.setLore(lores);

        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        item.setItemMeta(meta);
        fireStaff = item;
    }

    private static void createMetorStaff() {
        ItemStack item = new ItemStack(Material.BLAZE_ROD, 1);  //Item
        ItemMeta meta = item.getItemMeta();                        //Item Data
        meta.setDisplayName("Metor Staff");
        meta.isUnbreakable();

        meta.addEnchant(Enchantment.LUCK, 1, false);

        List<String> lores = new ArrayList<>();                    //Item Text
        lores.add("Right click to cast Metor");
        lores.add("Item Ability: Metor");
        meta.setLore(lores);

        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        item.setItemMeta(meta);
        metorStaff = item;
    }

    private static void createMagicBow() {
        ItemStack item = new ItemStack(Material.BOW, 1);  //Item
        ItemMeta meta = item.getItemMeta();                        //Item Data
        meta.setDisplayName("Magic Bow");
        meta.isUnbreakable();

        meta.addEnchant(Enchantment.LUCK, 1, false);

        List<String> lores = new ArrayList<>();                    //Item Text
        lores.add("Right click to cast Tripleshot");
        lores.add("Item Ability: Tripleshot");
        meta.setLore(lores);

        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        item.setItemMeta(meta);
        magicBow = item;
    }
}
