package commands;

import listener.blockbreak;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BlockBreakToggle implements CommandExecutor{

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        boolean blockbreaktrigger = blockbreak.getblockbreaktrigger();

        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (!player.hasPermission("server.break")) {

                player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                return false;
            }

            if (blockbreaktrigger) {
                blockbreak.setblockbreaktrigger(false);
                sender.sendMessage(ChatColor.GREEN + "You have enabled Block breaking");


        } else {

            blockbreak.setblockbreaktrigger(true);
            sender.sendMessage(ChatColor.RED + "You have disabled Block breaking");

           }
        }
        return true;
    }
}

