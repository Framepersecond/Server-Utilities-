package commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LumberCommand implements CommandExecutor {

    private final String TOGGLE_GUI_TITLE = ChatColor.GREEN + "Tree Felling Toggle";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (!player.hasPermission("server.lumber")) {
                player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                return false;
            }

            openToggleGUI(player);
            return true;
        } else {
            sender.sendMessage(ChatColor.RED + "This command can only be used by players!");
            return false;
        }
    }

    private void openToggleGUI(Player player) {
        Inventory gui = Bukkit.createInventory(null, 9, TOGGLE_GUI_TITLE);

        ItemStack toggleOn = new ItemStack(Material.GREEN_WOOL);
        ItemMeta onMeta = toggleOn.getItemMeta();
        assert onMeta != null;
        onMeta.setDisplayName(ChatColor.GREEN + "Enable Tree Felling");
        toggleOn.setItemMeta(onMeta);

        ItemStack toggleOff = new ItemStack(Material.RED_WOOL);
        ItemMeta offMeta = toggleOff.getItemMeta();
        assert offMeta != null;
        offMeta.setDisplayName(ChatColor.RED + "Disable Tree Felling");
        toggleOff.setItemMeta(offMeta);

        gui.setItem(3, toggleOn);
        gui.setItem(5, toggleOff);

        player.openInventory(gui);
    }
}

