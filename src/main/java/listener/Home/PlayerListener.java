package listener.Home;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import main.main;

import java.util.UUID;

public class PlayerListener implements Listener {

    public final main plugin;
    public PlayerListener(main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {

        if(!event.getFrom().getBlock().equals(event.getTo().getBlock())) {

            Player p = event.getPlayer();
            UUID id = p.getUniqueId();
            if(plugin.isQued(id));
            plugin.cancelQue(id);

        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {

        if(event.getEntity() instanceof Player) {

            Player p = (Player) event.getEntity();
            UUID id = p.getUniqueId();
            if(plugin.isQued(id));
            plugin.cancelQue(id);

        }
    }
}
