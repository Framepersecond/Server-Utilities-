package commands;

import listener.backlistener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.eclipse.sisu.launch.Main;
import main.main;

public class BackCommand implements CommandExecutor {

    private final main plugin;

    public BackCommand(main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;

        // Retrieve the death location
        Location deathLocation = backlistener.getDeathLocation(player);

        if (deathLocation == null) {
            player.sendMessage(ChatColor.RED + "No death location found!");
            return true;
        }

        // Set a delay (in seconds)
        int teleportDelay = 5;

        // Notify the player
        player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "================");
        player.sendMessage(ChatColor.DARK_AQUA + "Teleporting in " + teleportDelay + " seconds...");
        player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "================");

        // Create a teleportation task with delay
        new BukkitRunnable() {
            int delay = teleportDelay;

            @Override
            public void run() {
                if (delay > 0) {
                    player.sendMessage(ChatColor.DARK_AQUA + "Teleporting in " + delay-- + " seconds...");
                } else {
                    // Perform teleportation
                    player.teleport(deathLocation);
                    player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "================");
                    player.sendMessage(ChatColor.DARK_AQUA + "You have been teleported to your last death location.");
                    player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "================");
                    this.cancel(); // Stop the task
                }
            }
        }.runTaskTimer(plugin, 0L, 20L); // Schedule task to run every second (20 ticks)

        return true;
    }
}
