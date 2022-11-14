package com.ratrpg.menus;

import com.ratrpg.Ratrpg;
import com.ratrpg.commands.CommandBase;
import com.ratrpg.utilities.Message;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Menu implements Listener {

    private String invName = "Test Inv";
    public Menu(Ratrpg plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin); // This is for the events, the commands

        new CommandBase("menu", "Opens the test menu", true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                if(!(sender instanceof Player))
                {
                    sender.sendMessage("Only players can run this command.");
                    return true;
                }

                Player player = (Player) sender;

                Inventory inv = Bukkit.createInventory(player, 9 * 3, invName);

                inv.setItem(11, getItem(new ItemStack(Material.DIAMOND_SWORD), "&9Selector", "&aClick to pick", "Fuck you"));
                inv.setItem(13, getItem(new ItemStack(Material.TORCH), "&9Selector 2", "&aClick to pick", "Fuck you"));
                inv.setItem(15, getItem(new ItemStack(Material.DIAMOND), "&9Selector 3", "&aClick to pisck", "Fuck dyou"));

                player.openInventory(inv);

                return true;
            }

            @Override
            public String getUsage() {
                return "Menu takes no arguments";
            }
        }.enableDelay(2);

    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if(!event.getView().getTitle().equals(invName)) {
            return;
        }

        //Use this as a trigger for X thing
        Player player = (Player) event.getWhoClicked();
        int slot = event.getSlot();

        event.setCancelled(true); //Stops from clicking/moving items
    }

    private ItemStack getItem(ItemStack item, String name, String ... lore) {
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
