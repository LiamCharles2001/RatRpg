package com.ratrpg.menus;

import com.ratrpg.Ratrpg;
import com.ratrpg.utilities.MenuUtil;
import com.ratrpg.utilities.Message;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
//TODO this needs to be renamed to skill or something
public class Profile implements Listener {

    private static final String invName = "Profile";
    private static Profile instance;

    public Profile(Ratrpg plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        instance = this;
    }

    public static Profile getInstance() {
        return instance;
    }

    public void openProfile(Player player) {
        Inventory inv = Bukkit.createInventory(player, 9 * 3, invName);

        inv.setItem(3, MenuUtil.getItem(new ItemStack(Material.FISHING_ROD), "Fishing", "&aLevel: ", "&a0/0"));
        inv.setItem(4, MenuUtil.getItem(new ItemStack(Material.WHITE_WOOL), "Tailoring", "&aLevel: ", "&a0/0"));
        inv.setItem(5, MenuUtil.getItem(new ItemStack(Material.IRON_HOE), "Gathering", "&aLevel: ", "&a0/0"));

        inv.setItem(12, MenuUtil.getItem(new ItemStack(Material.IRON_PICKAXE), "Mining", "&aLevel: ", "&a0/0"));
        inv.setItem(13, MenuUtil.getItem(new ItemStack(Material.ANVIL), "Blacksmithing", "&aLevel: ", "&a0/0"));
        inv.setItem(14, MenuUtil.getItem(new ItemStack(Material.BOW), "Hunting", "&aLevel: ", "&a0/0"));

        inv.setItem(21, MenuUtil.getItem(new ItemStack(Material.IRON_AXE), "Woodcutting", "&aLevel: ", "&a0/0"));
        inv.setItem(22, MenuUtil.getItem(new ItemStack(Material.MUSHROOM_STEW), "Cooking", "&aLevel: ", "&a0/0"));
        inv.setItem(23, MenuUtil.getItem(new ItemStack(Material.NETHERITE_SWORD), "Slayer", "&aLevel: ", "&a0/0"));

        player.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals(invName)) {
            return;
        }

        event.setCancelled(true);

        Player player = (Player) event.getWhoClicked();
        int slot = event.getSlot();

        switch (slot){
            case 5:
                Message.send(player, "click");
                break;
        }
    }
}
