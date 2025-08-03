package managers;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class BanManager {
    private final File banFile;
    private final FileConfiguration banConfig;

    public BanManager(File dataFolder) {
        banFile = new File(dataFolder, "bans.yml");
        if (!banFile.exists()) {
            try {
                banFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        banConfig = YamlConfiguration.loadConfiguration(banFile);
    }

    public void banPlayer(UUID playerUUID, String staffName, String reason, long duration) {
        long banEndTime = duration > 0 ? System.currentTimeMillis() + duration : -1;
        banConfig.set(playerUUID + ".staff", staffName);
        banConfig.set(playerUUID + ".reason", reason);
        banConfig.set(playerUUID + ".end", banEndTime);
        saveBans();
    }

    public boolean isBanned(UUID playerUUID) {
        if (!banConfig.contains(playerUUID.toString())) {
            return false;
        }
        long banEndTime = banConfig.getLong(playerUUID + ".end", -1);
        if (banEndTime > 0 && System.currentTimeMillis() > banEndTime) {
            unbanPlayer(playerUUID);
            return false;
        }
        return true;
    }

    public String getBanReason(UUID playerUUID) {
        return banConfig.getString(playerUUID + ".reason", "No reason provided");
    }

    public String getStaffName(UUID playerUUID) {
        return banConfig.getString(playerUUID + ".staff", "Unknown");
    }

    public long getBanEndTime(UUID playerUUID) {
        return banConfig.getLong(playerUUID + ".end", -1);
    }

    public void unbanPlayer(UUID playerUUID) {
        banConfig.set(playerUUID.toString(), null);
        saveBans();
    }

    private void saveBans() {
        try {
            banConfig.save(banFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
