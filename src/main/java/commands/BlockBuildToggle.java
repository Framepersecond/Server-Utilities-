package commands;

import listener.blockbuild;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BlockBuildToggle implements CommandExecutor{

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        boolean blockbuildtrigger = blockbuild.getblockbuildtrigger();

        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (!player.hasPermission("server.build")) {

                player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                return false;
            }

            if (blockbuildtrigger) {
                blockbuild.setblockbuildtrigger(false);
                sender.sendMessage(ChatColor.GREEN + "You have enabled Block placing");


            } else {

                blockbuild.setblockbuildtrigger(true);
                sender.sendMessage(ChatColor.RED + "You have disabled Block placing");

            }
        }
        return true;
    }
}