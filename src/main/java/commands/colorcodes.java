package commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class colorcodes implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (!player.hasPermission("server.cc")) {

                player.sendMessage(ChatColor.RED + "You don't have permission to use this command");

                return false;

            }

            player.sendMessage(ChatColor.DARK_AQUA + "All color codes: ");
                    player.sendMessage(ChatColor.WHITE + " ChatColor Black: " + ChatColor.BLACK + "&0" );
                    player.sendMessage(ChatColor.WHITE + " ChatColor Dark_Blue: " + ChatColor.DARK_BLUE + "&1" );
                    player.sendMessage(ChatColor.WHITE + " ChatColor Dark_Green: " + ChatColor.DARK_GREEN + "&2" );
                    player.sendMessage(ChatColor.WHITE + " ChatColor Dark_Aqua: " + ChatColor.DARK_AQUA + "&3" );
                    player.sendMessage(ChatColor.WHITE + " ChatColor Dark_Red: " + ChatColor.DARK_RED + "&4" );
                    player.sendMessage(ChatColor.WHITE + " ChatColor Dark_Purple: " + ChatColor.DARK_PURPLE + "&5");
                    player.sendMessage(ChatColor.WHITE + " ChatColor Gold: " + ChatColor.GOLD + "&6" );
                    player.sendMessage(ChatColor.WHITE + " ChatColor Gray: " + ChatColor.GRAY + "&7" );
                    player.sendMessage(ChatColor.WHITE + " ChatColor Dark_Gray: " + ChatColor.DARK_GRAY + "&8");
                    player.sendMessage(ChatColor.WHITE + " Bold: " + ChatColor.WHITE + ChatColor.BOLD + "&l" );

        } else {

            ConsoleCommandSender console = Bukkit.getConsoleSender();
            console.sendMessage("This command can only be run as a player.");
        }
        return true;
    }
}
