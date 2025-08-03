package commands;

import API.PermissionsAPI;  // Adjust according to your API
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class MsgCommand implements CommandExecutor {

    private final PermissionsAPI permsapi = PermissionsAPI.getInstance();

    private final Map<String, String> lastMessages = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (!player.hasPermission("server.msg")) {
                player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                return false;
            }

            if (args.length < 2) {
                player.sendMessage(ChatColor.RED + "Usage: /msg <player> <message>");
                return false;
            }

            String targetPlayerName = args[0];
            StringBuilder messageBuilder = new StringBuilder();

            for (int i = 1; i < args.length; i++) {
                messageBuilder.append(args[i]).append(" ");
            }

            String message = messageBuilder.toString().trim();

            Player targetPlayer = Bukkit.getPlayer(targetPlayerName);

            if (targetPlayer == null) {
                player.sendMessage(ChatColor.RED + "Player " + targetPlayerName + " is not online.");
                return false;
            }

            targetPlayer.sendMessage(ChatColor.DARK_AQUA + "Message from " + player.getName() + ": " + ChatColor.WHITE + message);

            player.sendMessage(ChatColor.DARK_AQUA + "Message to " + targetPlayerName + ": " + ChatColor.WHITE + message);

            lastMessages.put(player.getName(), message);
            lastMessages.put(targetPlayer.getName(), message);

        } else {

            sender.sendMessage(ChatColor.GOLD + "You cannot use this command as the console.");
        }

        return true;
    }
}

