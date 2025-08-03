package listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class blockbreak implements Listener {

    public static boolean blockbreaktrigger = false;
    public static boolean getblockbreaktrigger() {
        return blockbreaktrigger;

    }

    public static void setblockbreaktrigger(boolean blockbreaktrigger2) {
        blockbreaktrigger = blockbreaktrigger2;

    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {

        e.setCancelled(blockbreaktrigger);

    }
}
