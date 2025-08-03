package managers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class WarnManager {

    private final File warnedPlayersFile;
    private final FileConfiguration warnedPlayersConfig;


    public WarnManager(File dataFolder) {
        warnedPlayersFile = new File(dataFolder, "warnedPlayers.yml");
        if (!warnedPlayersFile.exists()) {
            try {
                warnedPlayersFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        warnedPlayersConfig = YamlConfiguration.loadConfiguration(warnedPlayersFile);
    }
    public void warnPlayer(UUID playerUUID, String staffName, String reason) {
        warnedPlayersConfig.set(playerUUID + ".staff", staffName);
        warnedPlayersConfig.set(playerUUID + ".reason", reason);
        savewarnedPlayers();
    }

    private void savewarnedPlayers() {
        try {
            warnedPlayersConfig.save(warnedPlayersFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removewarnedPlayers(UUID playerUUID) {

        if (warnedPlayersConfig.contains(playerUUID.toString())) {

            warnedPlayersConfig.set(playerUUID.toString(), null);

            savewarnedPlayers();

            System.out.println("Removed warning for player UUID: " + playerUUID);
        } else {
            System.out.println("Player UUID not found in warnedPlayersConfig: " + playerUUID);
        }
    }


    public boolean iswarned(UUID playerUUID, String staffUUID) {

        Player staff = Bukkit.getPlayer(staffUUID);

        if (warnedPlayersConfig.contains(playerUUID.toString())) {

            staff.sendMessage(ChatColor.GOLD + "===================================================");
            staff.sendMessage(ChatColor.BOLD + "" + ChatColor.DARK_RED + "Player has already been warned.");
            staff.sendMessage(ChatColor.BOLD + "" + ChatColor.RED + "Please pursue further punishments");
            staff.sendMessage(ChatColor.BOLD + "" + ChatColor.DARK_AQUA + "Staff: " + ChatColor.WHITE + warnedPlayersConfig.getString(playerUUID + ".staff"));
            staff.sendMessage(ChatColor.BOLD + "" + ChatColor.LIGHT_PURPLE + "Reason: " + ChatColor.WHITE + warnedPlayersConfig.getString(playerUUID + ".reason"));
            staff.sendMessage(ChatColor.BOLD + "" + ChatColor.DARK_PURPLE + "UUID: " + ChatColor.WHITE + warnedPlayersConfig.getString(playerUUID.toString()));
            staff.sendMessage(ChatColor.GOLD + "===================================================");
            return false;
        }
        return true;
    }
    public String getreason (UUID playerUUID) {

        return warnedPlayersConfig.getString(".reason");

    }

    public String getstaff (UUID playerUUID) {

        return warnedPlayersConfig.getString(".staff");
    }
}
