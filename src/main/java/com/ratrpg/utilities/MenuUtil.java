package com.ratrpg.utilities;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MenuUtil {

    public static ItemStack getItem(ItemStack item, String name, String... lore) {
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(Message.color(name));

        List<String> lores = new ArrayList<>();
        for (String s : lore) {
            lores.add(Message.color(s));
        }

        meta.setLore(lores);

        item.setItemMeta(meta);
        return item;
    }
}
