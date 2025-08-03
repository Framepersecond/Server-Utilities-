package commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RulesCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (!player.hasPermission("server.rules")) {
                player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                return false;
            }

            player.sendMessage(ChatColor.GREEN + "You are executing this command as a player.");

        } else {
            sender.sendMessage(ChatColor.GOLD + "You are executing this command as the console.");
        }

        // Show the rules in the chat
        sender.sendMessage(ChatColor.GOLD + "=== Server Rules ===");
        sender.sendMessage(ChatColor.DARK_AQUA + "1. Be respectful of other players.");
        sender.sendMessage(ChatColor.DARK_AQUA + "2. Griefing is NOT prohibited.");
        sender.sendMessage(ChatColor.DARK_AQUA + "3. Funny pranks are allowed as long as they are reversible.");
        sender.sendMessage(ChatColor.DARK_AQUA + "4. Build in agreed areas.");
        sender.sendMessage(ChatColor.DARK_AQUA + "5. No spam and no advertising.");
        sender.sendMessage(ChatColor.DARK_AQUA + "6. No use of cheats and exploits.");
        sender.sendMessage(ChatColor.DARK_AQUA + "7. Build creatively, but not overly so.");
        sender.sendMessage(ChatColor.DARK_AQUA + "8. No unauthorized conflicts.");
        sender.sendMessage(ChatColor.DARK_AQUA + "9. Stay active and log out.");
        sender.sendMessage(ChatColor.DARK_AQUA + "10. Have fun!");
        sender.sendMessage(ChatColor.GOLD + "======================");
        return true;
    }
}
