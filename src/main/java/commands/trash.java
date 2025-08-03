package commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class trash implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        Player p = (Player) commandSender;

        if (!p.hasPermission("server.trash")) {
            p.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            return false;
        }

        if (commandSender instanceof Player) {

            Inventory trashInventory = Bukkit.createInventory(p, 54, ChatColor.DARK_AQUA + "Trashcan : " + ChatColor.GOLD + ChatColor.BOLD + p.getDisplayName() + ChatColor.RED + ChatColor.BOLD + " Everything in here will be deleted if closed");

            ItemStack lockedItem = new ItemStack(Material.DIRT);

            ItemMeta lockedItemMeta = lockedItem.getItemMeta();
            lockedItemMeta.setDisplayName("Trashcan");
            ArrayList<String> lore = new ArrayList<>();
            lore.add(ChatColor.DARK_RED + "Don't put anything important into here it will be deleted");
            lockedItemMeta.setLore(lore);
            lockedItem.setItemMeta(lockedItemMeta);

            trashInventory.setItem(0, lockedItem);

            p.openInventory(trashInventory);

        } else {
            p.sendMessage(ChatColor.RED + "You must be a player to use this command");
        }
        return false;
    }
}
