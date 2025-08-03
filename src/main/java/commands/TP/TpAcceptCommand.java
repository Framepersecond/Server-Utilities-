package commands.TP;

import main.main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpAcceptCommand implements CommandExecutor {

    private final main plugin;

    public TpAcceptCommand(main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be executed by players.");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("server.tpa")) {
            player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            return true;
        }

        Player requester = plugin.getTeleportRequests().get(player);

        if (requester == null || !requester.isOnline()) {
            player.sendMessage(ChatColor.RED + "No pending teleport request found.");
            return true;
        }

        requester.teleport(player);
        player.sendMessage(ChatColor.GREEN + "You accepted the teleport request from " + requester.getName() + ".");
        requester.sendMessage(ChatColor.GREEN + player.getName() + " accepted your teleport request.");

        plugin.getTeleportRequests().remove(player);

        return true;
    }
}
