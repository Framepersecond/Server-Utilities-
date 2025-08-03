package commands;

import API.PermissionsAPI;
import listener.ServerguiListener;
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
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ServerCommand implements CommandExecutor {

    private final ServerguiListener serverListener;

    public ServerCommand(ServerguiListener listener) {
        this.serverListener = listener;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return false;
        }

        Player player = (Player) sender;

        if(!player.hasPermission("server.GUI")) {

            player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            return false;
        }

        PermissionsAPI permsapi = PermissionsAPI.getInstance();

        String group = permsapi.getPlayersGroup(player);

        if (group == null) {
            player.sendMessage(ChatColor.RED + "Unable to determine your group.");
            return false;
        }

        switch (group.toLowerCase()) {
            case "user":
                openUserGUI(player);
                break;
            case "moderator":
            case "developer":
                openModeratorGUI(player);
                break;
            case "admin":
            case "owner":
                openAdminGUI(player);
                break;
            default:
                player.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
        }

        return true;
    }

    private void openUserGUI(Player player) {
        Inventory userGui = Bukkit.createInventory(null, 18, ChatColor.BLUE + "User Commands");

        userGui.setItem(0, createGuiItem(Material.CHEST, ChatColor.GOLD + "/bp", "Open your backpack."));
        userGui.setItem(1, createGuiItem(Material.BOOK, ChatColor.AQUA + "/colorcodes", "Show all color codes."));
        userGui.setItem(2, createGuiItem(Material.IRON_AXE, ChatColor.GREEN + "/lumber", "Toggle tree felling."));
        userGui.setItem(3, createGuiItem(Material.NAME_TAG, ChatColor.RED + "/prefix reset", "Reset your prefix."));
        userGui.setItem(4, createGuiItem(Material.BARRIER, ChatColor.DARK_RED + "/trash", "Open the trash can."));
        userGui.setItem(5, createGuiItem(Material.CLOCK, ChatColor.BLUE + "/weather", "Change the weather."));
        userGui.setItem(6, createGuiItem(Material.PAPER, ChatColor.DARK_AQUA + "/list", "Lists all online Players"));
        userGui.setItem(7, createGuiItem(Material.BOOK, ChatColor.DARK_RED + "/rules", "Displays Server Rules in Chat"));
        userGui.setItem(8, createGuiItem(Material.RED_DYE, ChatColor.DARK_AQUA + "/Colorcodes", "Displays Server Color Codes In Chat"));
        userGui.setItem(9, createGuiItem(Material.LEGACY_BOOK_AND_QUILL, ChatColor.DARK_PURPLE + "/motd", "Displays the Message of the Day in Chat"));
        userGui.setItem(10, createGuiItem(Material.CRAFTING_TABLE, ChatColor.GREEN + "/home", "Teleports you to your home"));
        userGui.setItem(11, createGuiItem(Material.LODESTONE, ChatColor.DARK_PURPLE + "/sethome", "Sets your home"));

        player.openInventory(userGui);
    }

    private void openModeratorGUI(Player player) {
        Inventory modGui = Bukkit.createInventory(null, 27, ChatColor.YELLOW + "Moderator Commands");

        modGui.setItem(0, createGuiItem(Material.CHEST, ChatColor.GOLD + "/bp", "Open your backpack."));
        modGui.setItem(1, createGuiItem(Material.BOOK, ChatColor.AQUA + "/colorcodes", "Show all color codes."));
        modGui.setItem(2, createGuiItem(Material.IRON_AXE, ChatColor.GREEN + "/lumber", "Toggle tree felling."));
        modGui.setItem(3, createGuiItem(Material.NAME_TAG, ChatColor.RED + "/prefix reset", "Reset your prefix."));
        modGui.setItem(4, createGuiItem(Material.BARRIER, ChatColor.DARK_RED + "/trash", "Open the trash can."));
        modGui.setItem(5, createGuiItem(Material.CLOCK, ChatColor.BLUE + "/weather", "Change the weather."));
        modGui.setItem(6, createGuiItem(Material.ANVIL, ChatColor.RED + "/bangui", "Open the ban GUI."));
        modGui.setItem(7, createGuiItem(Material.BREAD, ChatColor.GREEN + "/feed", "Feed a player."));
        modGui.setItem(8, createGuiItem(Material.FEATHER, ChatColor.LIGHT_PURPLE + "/fly", "Enable flying for a player."));
        modGui.setItem(9, createGuiItem(Material.GRASS_BLOCK, ChatColor.BLUE + "/gm", "Change game mode."));
        modGui.setItem(10, createGuiItem(Material.GOLDEN_APPLE, ChatColor.GREEN + "/heal", "Heal a player."));
        modGui.setItem(11, createGuiItem(Material.IRON_SWORD, ChatColor.RED + "/kick", "Kick a player."));
        modGui.setItem(12, createGuiItem(Material.TNT, ChatColor.DARK_RED + "/kill", "Kill a player."));
        modGui.setItem(13, createGuiItem(Material.BOOK, ChatColor.BLUE + "/staff", "Open the staff list."));
        modGui.setItem(14, createGuiItem(Material.ENDER_PEARL, ChatColor.DARK_PURPLE + "/vanish", "Toggle vanish mode."));
        modGui.setItem(15, createGuiItem(Material.PAPER, ChatColor.DARK_AQUA + "/list", "Lists all online Players"));
        modGui.setItem(16, createGuiItem(Material.BOOK, ChatColor.DARK_RED + "/rules", "Displays Server Rules in Chat"));
        modGui.setItem(17, createGuiItem(Material.RED_DYE, ChatColor.DARK_AQUA + "/Colorcodes", "Displays Server Color Codes In Chat"));
        modGui.setItem(18, createGuiItem(Material.LEGACY_BOOK_AND_QUILL, ChatColor.DARK_PURPLE + "/motd", "Displays the Message of the Day in Chat"));
        modGui.setItem(19, createGuiItem(Material.CRAFTING_TABLE, ChatColor.GREEN + "/home", "Teleports you to your home"));
        modGui.setItem(20, createGuiItem(Material.LODESTONE, ChatColor.DARK_PURPLE + "/sethome", "Sets your home"));

        player.openInventory(modGui);
    }

    private void openAdminGUI(Player player) {
        Inventory adminGui = Bukkit.createInventory(null, 27, ChatColor.RED + "Admin Commands");

        adminGui.setItem(0, createGuiItem(Material.CHEST, ChatColor.GOLD + "/bp", "Open your backpack."));
        adminGui.setItem(1, createGuiItem(Material.BOOK, ChatColor.AQUA + "/colorcodes", "Show all color codes."));
        adminGui.setItem(2, createGuiItem(Material.IRON_AXE, ChatColor.GREEN + "/lumber", "Toggle tree felling."));
        adminGui.setItem(3, createGuiItem(Material.NAME_TAG, ChatColor.RED + "/prefix reset", "Reset your prefix."));
        adminGui.setItem(4, createGuiItem(Material.BARRIER, ChatColor.DARK_RED + "/trash", "Open the trash can."));
        adminGui.setItem(5, createGuiItem(Material.CLOCK, ChatColor.BLUE + "/weather", "Change the weather."));
        adminGui.setItem(6, createGuiItem(Material.ANVIL, ChatColor.RED + "/bangui", "Open the ban GUI."));
        adminGui.setItem(7, createGuiItem(Material.BREAD, ChatColor.GREEN + "/feed", "Feed a player."));
        adminGui.setItem(8, createGuiItem(Material.FEATHER, ChatColor.LIGHT_PURPLE + "/fly", "Enable flying for a player."));
        adminGui.setItem(9, createGuiItem(Material.GRASS_BLOCK, ChatColor.BLUE + "/gm", "Change game mode."));
        adminGui.setItem(10, createGuiItem(Material.GOLDEN_APPLE, ChatColor.GREEN + "/heal", "Heal a player."));
        adminGui.setItem(11, createGuiItem(Material.IRON_SWORD, ChatColor.RED + "/kick", "Kick a player."));
        adminGui.setItem(12, createGuiItem(Material.TNT, ChatColor.DARK_RED + "/kill", "Kill a player."));
        adminGui.setItem(13, createGuiItem(Material.BOOK, ChatColor.BLUE + "/staff", "Open the staff list."));
        adminGui.setItem(14, createGuiItem(Material.ENDER_PEARL, ChatColor.DARK_PURPLE + "/vanish", "Toggle vanish mode."));
        adminGui.setItem(15, createGuiItem(Material.DIAMOND_PICKAXE, ChatColor.GOLD + "/break", "Enable break mode."));
        adminGui.setItem(16, createGuiItem(Material.DIAMOND_BLOCK, ChatColor.BLUE + "/build", "Enable build mode."));
        adminGui.setItem(17, createGuiItem(Material.PAPER, ChatColor.DARK_AQUA + "/list", "Lists all online Players"));
        adminGui.setItem(18, createGuiItem(Material.BOOK, ChatColor.DARK_RED + "/rules", "Displays Server Rules in Chat"));
        adminGui.setItem(19, createGuiItem(Material.RED_DYE, ChatColor.DARK_AQUA + "/Colorcodes", "Displays Server Color Codes In Chat"));
        adminGui.setItem(20, createGuiItem(Material.LEGACY_BOOK_AND_QUILL, ChatColor.DARK_PURPLE + "/motd", "Displays the Message of the Day in Chat"));
        adminGui.setItem(21, createGuiItem(Material.CRAFTING_TABLE, ChatColor.GREEN + "/home", "Teleports you to your home"));
        adminGui.setItem(22, createGuiItem(Material.LODESTONE, ChatColor.DARK_PURPLE + "/sethome", "Sets your home"));
        player.openInventory(adminGui);
    }

    private ItemStack createGuiItem(Material material, String name, String lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            ArrayList<String> itemLore = new ArrayList<>();
            itemLore.add(ChatColor.GRAY + lore);
            meta.setLore(itemLore);
            item.setItemMeta(meta);
        }
        return item;
    }
}


 
 