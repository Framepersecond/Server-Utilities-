package listener;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.HashMap;

public class backlistener implements Listener {

    private static final HashMap<Player, Location> deathLocations = new HashMap<>();

    public static Location getDeathLocation(Player player) {
        return deathLocations.get(player);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Location deathLocation = player.getLocation();

        // Save the death location
        deathLocations.put(player, deathLocation);
    }
}
