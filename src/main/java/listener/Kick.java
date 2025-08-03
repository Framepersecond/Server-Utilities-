package listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Kick implements Listener {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player p = (Player) sender;

            if (!p.hasPermission("server.kick")) {

                p.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                return false;


            } else if (args.length == 0) {

                p.setHealth(20);
                p.sendMessage(ChatColor.GREEN + "You have been healed!");

            }
            
            if (args.length == 1) {

                String playerName = args[0];
                Player target = Bukkit.getServer().getPlayerExact(playerName);

                if (target == null) {

                    p.sendMessage(ChatColor.RED + "That player is not online!");

                } else if (target.isOnline()) {

                    target.kickPlayer("");
                    p.sendMessage(ChatColor.DARK_RED + "You have kicked " + ChatColor.GOLD + ChatColor.BOLD + target.getDisplayName());
                    target.sendMessage(ChatColor.DARK_RED + "You have been kicked by " + ChatColor.GOLD + ChatColor.BOLD + p.getDisplayName());

                }

            } else if (args.length >= 2) {

                p.sendMessage(ChatColor.DARK_RED + "Invalid Format");
                p.sendMessage(ChatColor.RED + "Usage: /kick [player]");

            }
        }
        return false;
    }
}
