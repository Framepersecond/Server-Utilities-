package commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class backpack implements CommandExecutor, Listener {

    private static final String BACKPACK_FILE = "plugins/Server/backpacks/serverbackpack";

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        Player p = (Player) commandSender;

        if (!p.hasPermission("server.bp")) {
            p.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            return false;
        }

        if (commandSender instanceof Player) {
            Inventory backpack = Bukkit.createInventory(p, 54, ChatColor.DARK_AQUA + "Backpack : " + p.getDisplayName());

            loadBackpack(p, backpack);

            p.openInventory(backpack);

        } else {
            p.sendMessage(ChatColor.RED + "You must be a player to use this command");
        }
        return true;
    }

    public static void saveBackpack(Player p, Inventory backpack) {
        File file = new File(BACKPACK_FILE + p.getUniqueId() + ".yml");

        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }

        YamlConfiguration config = new YamlConfiguration();
        List<Map<String, Object>> itemList = new ArrayList<>();

        for (ItemStack item : backpack.getContents()) {
            if (item != null) {
                itemList.add(item.serialize());
            } else {
                itemList.add(null);
            }
        }

        config.set("items", itemList);

        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadBackpack(Player p, Inventory backpack) {
        File file = new File(BACKPACK_FILE + p.getUniqueId() + ".yml");

        if (!file.exists()) {
            return;
        }

        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        List<Map<?, ?>> itemList = config.getMapList("items");

        for (int i = 0; i < itemList.size(); i++) {
            Map<?, ?> itemMap = itemList.get(i);
            if (itemMap != null) {
                try {
                    ItemStack item = ItemStack.deserialize((Map<String, Object>) itemMap);
                    if (i < backpack.getSize()) {
                        backpack.setItem(i, item);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getView().getTitle().startsWith(ChatColor.DARK_AQUA + "Backpack : ")) {
            Player player = (Player) event.getPlayer();
            Inventory inventory = event.getInventory();
            saveBackpack(player, inventory);
        }
    }
}
