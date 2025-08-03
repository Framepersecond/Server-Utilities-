package listener;

import API.PermissionsAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;


public class MenuListeners implements Listener {

    PermissionsAPI permsapi = PermissionsAPI.getInstance();

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        Player player = (Player) e.getWhoClicked();
        String group = permsapi.getPlayersGroup(player);


        if (group.equals("admin")) {

            if (e.getView().getTitle().contains("Administrator")) {

                e.setCancelled(true);


                switch (e.getCurrentItem().getType()) {

                    case BEDROCK:

                        player.performCommand("break");
                        player.closeInventory();

                        break;

                    case STONE_SWORD:

                        player.performCommand("kill");
                        player.closeInventory();

                        break;

                    case COOKED_BEEF:

                        player.performCommand("feed");
                        player.closeInventory();

                        break;

                    case GOLDEN_BOOTS:

                        player.performCommand("fly");
                        player.closeInventory();

                        break;

                    case GOLDEN_APPLE:

                        player.performCommand("heal");
                        player.closeInventory();

                        break;

                }
            }
        } else if (group.equals("moderator")) {

            if (e.getView().getTitle().contains("Moderator")) {

                e.setCancelled(true);

                switch (e.getCurrentItem().getType()) {

                    case STONE_SWORD:

                        player.performCommand("kill");
                        player.closeInventory();

                        break;

                    case COOKED_BEEF:

                        player.performCommand("feed");
                        player.closeInventory();

                        break;

                    case GOLDEN_BOOTS:

                        player.performCommand("fly");
                        player.closeInventory();

                        break;

                    case GOLDEN_APPLE:

                        player.performCommand("heal");
                        player.closeInventory();

                        break;

                }
            }
        }
    }
}
