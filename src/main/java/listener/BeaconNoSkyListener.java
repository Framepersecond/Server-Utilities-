package listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Beacon;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class BeaconNoSkyListener implements Listener {

    private final JavaPlugin plugin;

    public BeaconNoSkyListener(JavaPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBeaconUpdate(BlockPhysicsEvent event) {
        Block block = event.getBlock();

        if (block.getType() == Material.BEACON) {
            forceBeaconActivation(block);
        }
    }

    @EventHandler
    public void onChunkLoad(ChunkLoadEvent event) {

        for (BlockState state : event.getChunk().getTileEntities()) {
            if (state instanceof Beacon beacon) {
                forceBeaconActivation(beacon.getBlock());
            }
        }
    }

    private void forceBeaconActivation(Block block) {

        if (block.getType() != Material.BEACON) return;

        Beacon beacon = (Beacon) block.getState();


        beacon.update();
    }

}


