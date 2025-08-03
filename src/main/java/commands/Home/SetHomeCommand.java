package commands.Home;

import main.main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class SetHomeCommand implements CommandExecutor {

    private final main plugin;
    public SetHomeCommand(main plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(@NotNull CommandSender sender, org.bukkit.command.@NotNull Command command, @NotNull String s, @NotNull String[] strings) {


         if (sender instanceof Player){
            Player p = (Player) sender;

             if (!p.hasPermission("server.home")) {
                 p.sendMessage(ChatColor.RED + "You dont have permission to use this command!");
                 return false;
             }

             UUID id = p.getUniqueId();
             Location location = p.getLocation();

            if (plugin.hasHome(id)){
                plugin.setHome(id, location);
                p.sendMessage(ChatColor.RED + "Your home has been overwritten");
            } else {
                plugin.addHome(id, location);
                p.sendMessage(ChatColor.DARK_AQUA + "Home set.");
                plugin.getFiles().addHome(id, location);
            }
         }
        return true;
    }
}
