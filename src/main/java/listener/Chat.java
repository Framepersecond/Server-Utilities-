package listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scoreboard.Team;

import static org.bukkit.Bukkit.getServer;

public class Chat implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        for (Team team : getServer().getScoreboardManager().getMainScoreboard().getTeams()) {
            if (team.hasPlayer(player)) {

                String prefix = team.getPrefix();


                if (prefix != null && !prefix.isEmpty()) {

                    String formattedPrefix = ChatColor.translateAlternateColorCodes('&', prefix);
                    String formattedMessage = formattedPrefix + player.getName() + ": " + ChatColor.translateAlternateColorCodes('&', event.getMessage());

                    event.setFormat(formattedMessage);
                    return;
                }
            }
        }

        event.setFormat(player.getName() + ": " + event.getMessage());
    }
}