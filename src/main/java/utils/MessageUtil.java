package utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageUtil {

    public static void showHelp(Player player) {
        player.sendMessage(ChatColor.GRAY + "Clan Plugin Hilfe:");
        player.sendMessage(ChatColor.AQUA + "/clan create [prefix] [name]" + ChatColor.GRAY + " - Erstelle einen neuen Clan.");
        player.sendMessage(ChatColor.AQUA + "/clan invite [player]" + ChatColor.GRAY + " - Lade einen Spieler in deinen Clan ein.");
        player.sendMessage(ChatColor.AQUA + "/clan kick [player]" + ChatColor.GRAY + " - Kicke einen Spieler aus deinem Clan.");
        player.sendMessage(ChatColor.AQUA + "/clan promote [mod/leader] [user]" + ChatColor.GRAY + " - Befördere einen Spieler.");
        player.sendMessage(ChatColor.AQUA + "/clan list" + ChatColor.GRAY + " - Zeige alle verfügbaren Clans.");
        player.sendMessage(ChatColor.AQUA + "/clan delete" + ChatColor.GRAY + " - Lösche deinen Clan.");
        player.sendMessage(ChatColor.AQUA + "/clan prefix [remove/newPrefix]" + ChatColor.GRAY + " - Ändere den Präfix deines Clans.");
        player.sendMessage(ChatColor.AQUA + "/clan sethome" + ChatColor.GRAY + " - Setze die Heimatposition deines Clans.");
        player.sendMessage(ChatColor.AQUA + "/clan home" + ChatColor.GRAY + " - Teleportiere dich zu deinem Clan-Home.");
        player.sendMessage(ChatColor.AQUA + "/clan pos [name]" + ChatColor.GRAY + " - Setze die Position deines Clans.");
    }
}


