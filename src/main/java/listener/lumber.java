package listener;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class lumber implements Listener {

    private final ConcurrentHashMap<UUID, Boolean> lumberToggle = new ConcurrentHashMap<>();
    private final String TOGGLE_GUI_TITLE = ChatColor.GREEN + "Tree Felling Toggle";

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();

        if (lumberToggle.getOrDefault(player.getUniqueId(), false)) {
            Block block = event.getBlock();

            if (isLog(block.getType())) {
                breakTree(block);
            }
        }
    }

    private void breakTree(Block startBlock) {
        Set<Block> visitedBlocks = new HashSet<>();
        breakTreeRecursive(startBlock, visitedBlocks);
    }

    private void breakTreeRecursive(Block block, Set<Block> visitedBlocks) {

        if (visitedBlocks.contains(block)) return;
        visitedBlocks.add(block);

        block.breakNaturally();

        for (Block neighbor : getAdjacentBlocks(block)) {
            if (isLog(neighbor.getType()) || isLeaf(neighbor.getType())) {
                breakTreeRecursive(neighbor, visitedBlocks);
            }
        }
    }

    private boolean isLog(Material material) {
        return material.name().endsWith("_LOG");
    }

    private boolean isLeaf(Material material) {
        return material.name().endsWith("_LEAVES");
    }

    private Set<Block> getAdjacentBlocks(Block block) {
        Set<Block> neighbors = new HashSet<>();
        neighbors.add(block.getRelative(1, 0, 0));
        neighbors.add(block.getRelative(-1, 0, 0));
        neighbors.add(block.getRelative(0, 1, 0));
        neighbors.add(block.getRelative(0, -1, 0));
        neighbors.add(block.getRelative(0, 0, 1));
        neighbors.add(block.getRelative(0, 0, -1));
        return neighbors;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals(TOGGLE_GUI_TITLE)) {
            event.setCancelled(true);

            Player player = (Player) event.getWhoClicked();
            if (event.getCurrentItem() == null || !event.getCurrentItem().hasItemMeta()) return;

            String displayName = event.getCurrentItem().getItemMeta().getDisplayName();
            if (displayName.equals(ChatColor.GREEN + "Enable Tree Felling")) {
                lumberToggle.put(player.getUniqueId(), true);
                player.sendMessage(ChatColor.GREEN + "Tree felling enabled!");
                player.closeInventory();
            } else if (displayName.equals(ChatColor.RED + "Disable Tree Felling")) {
                lumberToggle.put(player.getUniqueId(), false);
                player.sendMessage(ChatColor.RED + "Tree felling disabled!");
                player.closeInventory();
            }
        }
    }
}
