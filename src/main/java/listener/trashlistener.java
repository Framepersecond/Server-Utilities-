package listener;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class trashlistener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        if (event.getView().getTitle().contains("Trashcan")) {

            ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem != null && clickedItem.getType() == Material.DIRT && clickedItem.getItemMeta() != null) {
                String displayName = clickedItem.getItemMeta().getDisplayName();

                if ("Trashcan".equals(displayName)) {
                    event.setCancelled(true);
                }
            }
        }
    }
}

