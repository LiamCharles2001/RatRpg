package com.ratrpg.menus;

import com.ratrpg.Ratrpg;
import com.ratrpg.data.PlayerMemory;
import com.ratrpg.utilities.CommandBase;
import com.ratrpg.utilities.MenuUtil;
import com.ratrpg.utilities.PlayerUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static com.ratrpg.data.PlayerClass.getClassName;

public class ProfileSelector implements Listener {

    private static final String invName = "Profile Selector";

    public ProfileSelector(Ratrpg plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);

        new CommandBase("profile", "Opens the profile menu", true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("Only players can run this command.");
                    return true;
                }

                Player player = (Player) sender;

                Inventory inv = Bukkit.createInventory(player, 9 * 3, invName);

                PlayerMemory memory = PlayerUtil.getPlayerMemory(player);

                inv.setItem(26, MenuUtil.getItem(new ItemStack(Material.DIAMOND),
                        getClassName(memory.getPlayerClass()),
                        "&9Profile Stats",
                        "&4Max Health: " + memory.getMaxHealth(),
                        "&3Max Mana: " + memory.getMaxMana(),
                        "Armor: " + memory.getArmor(),
                        "&aAgility: " + memory.getAgility(),
                        "&cStrength: " + memory.getStrength(),
                        "&bIntelligent: " + memory.getIntelligent()));


                inv.setItem(11, MenuUtil.getItem(new ItemStack(Material.BOOK), "&9Profile 1", "&aClick to switch profiles"));
                inv.setItem(13, MenuUtil.getItem(new ItemStack(Material.BOOK), "&9Profile 2", "&aClick to switch profiles"));
                inv.setItem(15, MenuUtil.getItem(new ItemStack(Material.BOOK), "&9Profile 3", "&aClick to switch profiles"));

                inv.setItem(2, MenuUtil.getItem(new ItemStack(Material.TRIDENT), "PROFILE - Skills", "&aClick to switch profiles"));
                inv.setItem(3, MenuUtil.getItem(new ItemStack(Material.TRIDENT), "CLASS SELECTOR", "&aClick to switch profiles"));
                inv.setItem(4, MenuUtil.getItem(new ItemStack(Material.RED_WOOL), "Reset Current Profile", "&aClick to switch profiles"));

                player.openInventory(inv);

                return true;
            }

            @Override
            public String getUsage() {
                return "Profile takes no arguments";
            }
        }.enableDelay(2);

    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals(invName)) {
            return;
        }

        event.setCancelled(true); //Stops from clicking/moving items

        //Use this as a trigger for X thing
        Player player = (Player) event.getWhoClicked();
        int slot = event.getSlot();

        switch(slot){
            case 11:
                //Profile 1
                PlayerUtil.savePlayerData(player);
                PlayerUtil.switchPlayerData(player, 1);
                player.closeInventory();
                break;
            case 13:
                //Profile 2
                PlayerUtil.savePlayerData(player);
                PlayerUtil.switchPlayerData(player, 2);
                player.closeInventory();
                break;
            case 15:
                //Profile 3
                PlayerUtil.savePlayerData(player);
                PlayerUtil.switchPlayerData(player, 3);
                player.closeInventory();
                break;
            case 2:
                Profile.getInstance().openProfile(player);
                break;
            case 3:
                ClassSelector.getInstance().openClassSelector(player);
                break;
            case 4:
                PlayerMemory memory = PlayerUtil.getPlayerMemory(player);
                PlayerUtil.newProfile(player, memory.getSelectedProfileId());
                player.closeInventory();
                break;
        }
    }
}
