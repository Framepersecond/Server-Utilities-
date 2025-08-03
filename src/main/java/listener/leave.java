package listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class leave implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {

        Player player = e.getPlayer();
        e.setQuitMessage(ChatColor.GOLD + "" + ChatColor.BOLD + player.getDisplayName() + ChatColor.DARK_AQUA + " has left the game");
    }
}
