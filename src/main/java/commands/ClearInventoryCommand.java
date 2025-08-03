package commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ClearInventoryCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if (!sender.hasPermission("server.clear")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
            return true;
        }

        if(args.length == 0) {



            sender.sendMessage(ChatColor.RED + "Usage: /clear <player>");


        } else if(args.length == 1 ) {

            Player target = Bukkit.getPlayer(args[0]);
            target.getInventory().clear();

            if (target != null) {

                target.getInventory().clear();
                sender.sendMessage(ChatColor.DARK_AQUA + "Cleared " + target.getName() + "'s inventory");
                target.sendMessage(ChatColor.RED + "Your Inventory has been cleared by " + sender.getName());

            } else {

                sender.sendMessage(ChatColor.RED + "This player is either ofline or doesnt exist");

            }

        } else if (args.length > 1) {

            sender.sendMessage(ChatColor.RED + "Usage: /clear <player>");

        }
        return false;
    }
}
