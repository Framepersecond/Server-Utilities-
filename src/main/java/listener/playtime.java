package listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class playtime implements Listener {

    private long serverPlayTime = 0;
    private Map<Player, Long> playerPlayTimes = new HashMap<>();
    private boolean isCounting = false;
    private boolean showPlayerTime = true;

    public void startTask(JavaPlugin plugin) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (Bukkit.getOnlinePlayers().isEmpty()) {
                    isCounting = false;
                    return;
                }

                isCounting = true;

                if (isCounting) {
                    serverPlayTime += 5;

                    for (Player player : Bukkit.getOnlinePlayers()) {
                        playerPlayTimes.put(player, playerPlayTimes.getOrDefault(player, 0L) + 5);
                    }

                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendActionBar(ChatColor.AQUA + "Server Playtime: " + formatTime(serverPlayTime));
                        player.sendTitle("", ChatColor.AQUA + "Server Playtime: " + formatTime(serverPlayTime), 0, 20, 0);
                        player.sendActionBar(ChatColor.GREEN + "Your Playtime: " + formatTime(playerPlayTimes.getOrDefault(player, 0L)));
                        player.sendTitle("", ChatColor.GREEN + "Your Playtime: " + formatTime(playerPlayTimes.getOrDefault(player, 0L)), 0, 20, 0);
                    }
                }
            }
        }.runTaskTimer(plugin, 0, 100);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        playerPlayTimes.putIfAbsent(player, 0L);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {

    }

    private String formatTime(long seconds) {
        long hrs = seconds / 3600;
        long mins = (seconds % 3600) / 60;
        long secs = seconds % 60;
        return String.format("%02d:%02d:%02d", hrs, mins, secs);
    }
}

