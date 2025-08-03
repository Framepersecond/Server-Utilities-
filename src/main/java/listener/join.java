package listener;

import API.PermissionsAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class join implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        PermissionsAPI permsapi = PermissionsAPI.getInstance();
        permsapi.givePlayersGroupPerms(player);

        if (player.hasPlayedBefore()) {
            e.setJoinMessage(ChatColor.DARK_AQUA + "Welcome Back " + ChatColor.GOLD + ChatColor.BOLD + player.getDisplayName());

        } else {
            e.setJoinMessage(ChatColor.DARK_AQUA + " Welcome to the Server " + ChatColor.GOLD + ChatColor.GOLD + player.getDisplayName() + "");
        }



    }
}
