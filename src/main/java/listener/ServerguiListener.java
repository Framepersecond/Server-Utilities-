package listener;

import main.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.List;

public class ServerguiListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        if (event.getView().getTitle().contains("Commands")) {
            event.setCancelled(true);

            ItemStack clickedItem = event.getCurrentItem();
            if (clickedItem == null || !clickedItem.hasItemMeta() || !clickedItem.getItemMeta().hasDisplayName()) {

            }

            Player player = (Player) event.getWhoClicked();
            String itemName = ChatColor.stripColor(clickedItem.getItemMeta().getDisplayName());

            switch (itemName.toLowerCase()) {
                case "/bp":
                    player.performCommand("bp");
                    player.sendMessage(ChatColor.DARK_AQUA + "Opening Backpack...");
                    break;
                case "/colorcodes":
                    player.performCommand("colorcodes");
                    break;
                case "/lumber":
                    player.performCommand("lumber");
                    break;
                case "/prefix reset":
                    player.performCommand("prefix reset");
                    player.sendMessage(ChatColor.RED + "Resetting Prefix...");
                    break;
                case "/trash":
                    player.performCommand("trash");
                    player.sendMessage(ChatColor.DARK_AQUA + "Opening Trash...");
                    break;
                case "/weather":
                    player.performCommand("weather");
                    break;
                case "/bangui":
                    player.performCommand("bangui");
                    player.sendMessage(ChatColor.RED + "Opening Ban GUI...");
                    break;
                case "/feed":
                    openPlayerMenu(player, "Feed");
                    break;
                case "/fly":
                    openPlayerMenu(player, "Fly");
                    break;
                case "/gm":
                    player.sendMessage(ChatColor.DARK_AQUA + "Opening Gamemode GUI...");
                    openGamemodeMenu(player);
                    break;
                case "/heal":
                    openPlayerMenu(player, "Heal");
                    break;
                case "/kick":
                    openPlayerMenu(player, "Kick");
                    break;
                case "/kill":
                    openPlayerMenu(player, "Kill");
                    break;
                case "/staff":
                    player.performCommand("staff");
                    player.sendMessage(ChatColor.RED + "Opening Staff Menu...");
                    break;
                case "/vanish":
                    openPlayerMenu(player, "Vanish");
                    break;
                case "/break":
                    player.performCommand("break");
                    break;
                case "/build":
                    player.performCommand("build");
                    break;
                case "/list":
                    player.performCommand("list");
                    break;
                case "/rules":
                    player.performCommand("rules");
                    break;
                case "/Colorcodes":
                    player.performCommand("colorcodes");
                    break;
                case "/motd":
                    player.performCommand("motd");
                    break;
                case "/home":
                    player.performCommand("home");
                    break;
                case "/sethome":
                    player.performCommand("sethome");
                    break;
                default:
                    player.sendMessage(ChatColor.RED + "Unknown command: " + itemName);
            }
        }

        if (event.getView().getTitle().startsWith(ChatColor.RED + "Select a Player to")) {
            event.setCancelled(true);

            ItemStack clickedItem = event.getCurrentItem();
            if (clickedItem == null || clickedItem.getType() != Material.PLAYER_HEAD) {
                return;
            }

            Player player = (Player) event.getWhoClicked();
            String action = ChatColor.stripColor(event.getView().getTitle().replace("Select a Player to ", ""));
            String targetName = ChatColor.stripColor(clickedItem.getItemMeta().getDisplayName());

            Player target = Bukkit.getPlayer(targetName);
            if (target == null) {
                player.sendMessage(ChatColor.RED + "Player " + targetName + " is no longer online.");
                return;
            }

            switch (action.toLowerCase()) {
                case "feed":
                    target.setFoodLevel(20);
                    target.setSaturation(20);
                    target.setFoodLevel(20);
                    target.sendMessage(ChatColor.GREEN + "You have been fed by " + ChatColor.GOLD + ChatColor.BOLD + player.getDisplayName());
                    player.sendMessage(ChatColor.GREEN + "You have fed! " + ChatColor.GOLD + ChatColor.BOLD +target.getDisplayName());
                    break;
                case "fly":
                    if (main.flytoggle) {

                        target.setAllowFlight(false);
                        main.flytoggle = false;
                        target.setFlying(false);
                        target.sendMessage(net.md_5.bungee.api.ChatColor.RED + "Your ability to fly has been disabled by " + net.md_5.bungee.api.ChatColor.GOLD + net.md_5.bungee.api.ChatColor.BOLD + player.getDisplayName());
                        player.sendMessage(net.md_5.bungee.api.ChatColor.RED + "You have disabled the ability to fly for " + net.md_5.bungee.api.ChatColor.GOLD + net.md_5.bungee.api.ChatColor.BOLD + target.getDisplayName());

                    } else {

                        target.setAllowFlight(true);
                        main.flytoggle = true;
                        target.setFlying(true);
                        target.sendMessage(net.md_5.bungee.api.ChatColor.GREEN + "Your ability to fly has been enabled by " + net.md_5.bungee.api.ChatColor.GOLD + net.md_5.bungee.api.ChatColor.BOLD + player.getDisplayName());
                        player.sendMessage(net.md_5.bungee.api.ChatColor.GREEN + "You have enabled the ability to fly for "  + net.md_5.bungee.api.ChatColor.GOLD + net.md_5.bungee.api.ChatColor.BOLD + target.getDisplayName());
                    }
                    break;
                case "heal":
                    target.setHealth(20);
                    player.sendMessage(ChatColor.GREEN + "You have healed " + ChatColor.GOLD + ChatColor.BOLD + target.getDisplayName());
                    target.sendMessage(ChatColor.GREEN + "You have been healed by " + ChatColor.GOLD + ChatColor.BOLD + player.getDisplayName());
                    break;
                case "kick":
                    target.kickPlayer(target.getName());
                    player.sendMessage(ChatColor.DARK_RED + "You have kicked " + ChatColor.GOLD + ChatColor.BOLD + target.getDisplayName());
                    target.sendMessage(ChatColor.DARK_RED + "You have been kicked by " + ChatColor.GOLD + ChatColor.BOLD + player.getDisplayName());
                    break;
                case "kill":
                    target.setHealth(0);
                    target.sendMessage(ChatColor.RED + "You have been killed by!" + ChatColor.GOLD + " " + ChatColor.BOLD + player.getDisplayName());
                    player.sendMessage(ChatColor.RED + "You have killed!" + ChatColor.GOLD + " " + ChatColor.BOLD + target.getDisplayName());
                    break;
                case "vanish":
                    player.performCommand("vanish");
                    break;
                default:
                    player.sendMessage(ChatColor.RED + "Unknown action: " + action);
            }

            player.closeInventory();
        }
    }

    public void openPlayerMenu(Player player, String action) {
        Inventory menu = Bukkit.createInventory(null, 54, ChatColor.RED + "Select a Player to " + action);

        for (Player target : Bukkit.getOnlinePlayers()) {
            ItemStack head = new ItemStack(Material.PLAYER_HEAD, 1);
            ItemMeta meta = head.getItemMeta();
            if (meta != null) {
                meta.setDisplayName(ChatColor.YELLOW + target.getName());
                meta.setLore(List.of(ChatColor.GRAY + "Click to " + action.toLowerCase() + " " + target.getName()));
                head.setItemMeta(meta);
            }
            menu.addItem(head);
        }

        player.openInventory(menu);
    }

    private void openGamemodeMenu(Player player) {
        Inventory gmMenu = Bukkit.createInventory(null, 9, ChatColor.BLUE + "Select Gamemode");

        gmMenu.setItem(0, createGuiItem(Material.GRASS_BLOCK, ChatColor.GREEN + "Survival", "Set gamemode to Survival"));
        gmMenu.setItem(1, createGuiItem(Material.BRICKS, ChatColor.GOLD + "Creative", "Set gamemode to Creative"));
        gmMenu.setItem(2, createGuiItem(Material.IRON_SWORD, ChatColor.RED + "Adventure", "Set gamemode to Adventure"));
        gmMenu.setItem(3, createGuiItem(Material.ENDER_PEARL, ChatColor.BLUE + "Spectator", "Set gamemode to Spectator"));

        player.openInventory(gmMenu);
    }

    private ItemStack createGuiItem(Material material, String name, String lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            meta.setLore(List.of(ChatColor.GRAY + lore));
            item.setItemMeta(meta);
        }
        return item;
    }
}
