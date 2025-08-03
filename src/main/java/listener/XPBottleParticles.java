package listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExpBottleEvent;

public class XPBottleParticles implements Listener {

    @EventHandler
    public void onXPBottleBreak(ExpBottleEvent event) {
        event.setShowEffect(false);

    }
}

