package listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class blockbuild implements Listener {

    public static boolean blockbuildtrigger = false;
    public static boolean getblockbuildtrigger() {
        return blockbuildtrigger;

    }

    public static void setblockbuildtrigger(boolean blockbuildtrigger2) {
        blockbuildtrigger = blockbuildtrigger2;

    }

    @EventHandler
    public void onBlockBuild(BlockPlaceEvent e) {

        e.setCancelled(blockbuildtrigger);

    }
}
