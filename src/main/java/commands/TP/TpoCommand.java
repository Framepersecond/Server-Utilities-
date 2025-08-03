package commands.TP;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TpoCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be executed by a player.");
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("server.tpo")) {
            player.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            return false;
        }

        if (args.length != 1) {
            player.sendMessage(ChatColor.RED + "Usage: /tpo <player>");
            return false;
        }

        String targetName = args[0];
        Player target = Bukkit.getPlayer(targetName);

        if (target == null) {
            player.sendMessage(ChatColor.RED + "Player not found or not online.");
            return false;
        }

        player.teleport(target.getLocation());
        player.sendMessage(ChatColor.GREEN + "You have been teleported to " + target.getName());

        return true;
    }
}
