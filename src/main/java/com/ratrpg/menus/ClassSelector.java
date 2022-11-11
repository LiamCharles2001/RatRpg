package com.ratrpg.menus;

import com.ratrpg.Ratrpg;
import com.ratrpg.data.PlayerClass;
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

import static com.ratrpg.utilities.PlayerUtil.hasNonePlayerClass;
import static com.ratrpg.utilities.PlayerUtil.switchClass;

public class ClassSelector implements Listener {
    private static final String invName = "Pick your class";
    private static ClassSelector instance;

    public ClassSelector(Ratrpg plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
        instance = this;
    }

    public static ClassSelector getInstance() {
        return instance;
    }

    public void openClassSelector(Player player) {
        Inventory inv = Bukkit.createInventory(player, 9 * 1, invName);

        inv.setItem(2, MenuUtil.getItem(new ItemStack(Material.ENCHANTED_BOOK), "Mage", "&aA Powerful spell caster that uses magic to blast their enemies"));
        inv.setItem(3, MenuUtil.getItem(new ItemStack(Material.SHIELD), "Paladin", "&aA holy hero that smites their enemies with holy light"));
        inv.setItem(4, MenuUtil.getItem(new ItemStack(Material.DIAMOND_SWORD), "Warrior", "&aLame"));
        inv.setItem(5, MenuUtil.getItem(new ItemStack(Material.BOW), "Ranger", "&aA man who shoots others"));
        inv.setItem(6, MenuUtil.getItem(new ItemStack(Material.IRON_SWORD), "Rogue", "&aA sneaky man who slabs people"));

        player.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals(invName)) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        event.setCancelled(true);

        if(!hasNonePlayerClass(player)) {
            Message.send(player, "You already have a class.");
            return;
        }

        int slot = event.getSlot();

        switch(slot) {
            case 2:
                switchClass(player, PlayerClass.MAGE);
                Message.send(player, "You've selected Mage.");
                break;
            case 3:
                switchClass(player, PlayerClass.PALADIN);
                Message.send(player, "You've selected Paladin.");
                break;
            case 4:
                switchClass(player, PlayerClass.WARRIOR);
                Message.send(player, "You've selected Warrior.");
                break;
            case 5:
                switchClass(player, PlayerClass.RANGER);
                Message.send(player, "You've selected Ranger.");
                break;
            case 6:
                switchClass(player, PlayerClass.ROGUE);
                Message.send(player, "You've selected Rogue.");
                break;
        }
    }
}
