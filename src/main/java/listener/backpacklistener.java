package listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import commands.backpack;

public class backpacklistener implements Listener {

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        if (event.getView().getTitle().startsWith(ChatColor.DARK_AQUA + "Backpack : " + player.getDisplayName())) {

            Inventory inventory = event.getInventory();
            backpack.saveBackpack(player, inventory);
        }
    }

}
