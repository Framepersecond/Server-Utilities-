package commands.Home;

import listener.Chat;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import main.main;

import java.util.UUID;

public class HomeCommand implements CommandExecutor {

    private final main plugin;

    public HomeCommand(main plugin)
    {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (sender instanceof Player) {

            Player p = (Player) sender;

            if (!p.hasPermission("server.home")) {
                p.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
                return false;
            }

            UUID id = p.getUniqueId();

            if (!plugin.hasHome(id)){

             p.sendMessage(ChatColor.RED + "You do not have a home set");

            } else {

                plugin.addQue(id );
                new BukkitRunnable() {

                    int delay = 5;
                    @Override
                    public void run() {

                        if(plugin.isQued(id)) {
                            if (delay == 0) {

                                p.teleport(plugin.getHome(id));
                                p.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "================");
                                p.sendMessage(ChatColor.DARK_AQUA + "Teleporting...");
                                p.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "================");
                                plugin.cancelQue(id);
                                this.cancel();
                            } else {
                                p.sendMessage(ChatColor.DARK_AQUA + "Teleporting in " + delay-- + " seconds.");
                            }
                        } else {
                            p.sendMessage(ChatColor.RED + "Teleportation cancelled.");
                            this.cancel();
                        }
                    }
                }.runTaskTimer(plugin, 0, 20);
            }
        }
        return true;
    }
}
