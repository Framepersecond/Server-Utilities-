package commands;

import API.PermissionsAPI;
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

import java.util.ArrayList;

public class menu implements CommandExecutor {

    @Override
    public  boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        PermissionsAPI permsapi = PermissionsAPI.getInstance();

        if (sender instanceof Player) {

            Player player = (Player) sender;

            if(!player.hasPermission("server.staff")) {

                player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                return false;
            }

            String group = permsapi.getPlayersGroup(player);

            if (group.equals("admin") || group.equals("owner") || group.equals("developer")) {

                Inventory menu = Bukkit.createInventory(player, 9, ChatColor.DARK_RED + "Administrator");

                ItemStack Break = new ItemStack(Material.BEDROCK);

                ItemStack die = new ItemStack(Material.STONE_SWORD);

                ItemStack feed  = new ItemStack(Material.COOKED_BEEF);

                ItemStack fly = new ItemStack(Material.GOLDEN_BOOTS);

                ItemStack heal = new ItemStack(Material.GOLDEN_APPLE);

                ItemMeta BreakMeta = Break.getItemMeta();
                BreakMeta.setDisplayName(ChatColor.RED + "Break");
                ArrayList<String> Breaklore = new ArrayList<String>();
                Breaklore.add(ChatColor.DARK_RED + "Toggles the Ability to Break blocks");
                BreakMeta.setLore(Breaklore);
                Break.setItemMeta(BreakMeta);

                menu.setItem(0, Break);

                ItemMeta DieMeta = die.getItemMeta();
                DieMeta.setDisplayName(ChatColor.RED + "Die");
                ArrayList<String> Dielore = new ArrayList<String>();
                Dielore.add(ChatColor.RED + "Kills you");
                DieMeta.setLore(Dielore);
                die.setItemMeta(DieMeta);

                menu.setItem(2, die);

                ItemMeta feedMeta = feed.getItemMeta();
                feedMeta.setDisplayName(ChatColor.RED + "Feed");
                ArrayList<String> feedlore = new ArrayList<String>();
                feedlore.add(ChatColor.DARK_GREEN + "Feed's You");
                feedMeta.setLore(feedlore);
                feed.setItemMeta(feedMeta);

                menu.setItem(4, feed);

                ItemMeta flyMeta = fly.getItemMeta();
                flyMeta.setDisplayName(ChatColor.RED + "Fly");
                ArrayList<String> flylore = new ArrayList<String>();
                flylore.add(ChatColor.DARK_GREEN + "Toggles the Ability to fly");
                flyMeta.setLore(flylore);
                fly.setItemMeta(flyMeta);

                menu.setItem(6, fly);

                ItemMeta healMeta = heal.getItemMeta();
                healMeta.setDisplayName(ChatColor.RED + "Heal");
                ArrayList<String> heallore = new ArrayList<String>();
                heallore.add(ChatColor.DARK_GREEN + "Heals you");
                healMeta.setLore(heallore);
                heal.setItemMeta(healMeta);

                menu.setItem(8, heal);

                player.openInventory(menu);

            } else if (group.equals("moderator")) {

                Inventory menu = Bukkit.createInventory(player, 9, ChatColor.DARK_RED + "Moderator");

                ItemStack die = new ItemStack(Material.STONE_SWORD);

                ItemStack feed  = new ItemStack(Material.COOKED_BEEF);

                ItemStack fly = new ItemStack(Material.GOLDEN_BOOTS);

                ItemStack heal = new ItemStack(Material.GOLDEN_APPLE);

                ItemMeta DieMeta = die.getItemMeta();
                DieMeta.setDisplayName(ChatColor.RED + "Die");
                ArrayList<String> Dielore = new ArrayList<String>();
                Dielore.add(ChatColor.RED + "Kills you");
                DieMeta.setLore(Dielore);
                die.setItemMeta(DieMeta);

                menu.setItem(1, die);

                ItemMeta feedMeta = feed.getItemMeta();
                feedMeta.setDisplayName(ChatColor.RED + "Feed");
                ArrayList<String> feedlore = new ArrayList<String>();
                feedlore.add(ChatColor.DARK_GREEN + "Feed's you");
                feedMeta.setLore(feedlore);
                feed.setItemMeta(feedMeta);

                menu.setItem(3, feed);

                ItemMeta flyMeta = fly.getItemMeta();
                flyMeta.setDisplayName(ChatColor.RED + "Fly");
                ArrayList<String> flylore = new ArrayList<String>();
                flylore.add(ChatColor.DARK_GREEN + "Toggles the Ability to fly");
                flyMeta.setLore(flylore);
                fly.setItemMeta(flyMeta);

                menu.setItem(5, fly);

                ItemMeta healMeta = heal.getItemMeta();
                healMeta.setDisplayName(ChatColor.RED + "Heal");
                ArrayList<String> heallore = new ArrayList<String>();
                heallore.add(ChatColor.DARK_GREEN + "Heals you");
                healMeta.setLore(heallore);
                heal.setItemMeta(healMeta);

                menu.setItem(7, heal);

                player.openInventory(menu);
            }
        }
        return true;
    }
}


