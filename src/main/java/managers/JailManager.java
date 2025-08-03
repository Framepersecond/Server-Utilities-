package managers;

import main.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class JailManager {

    private final HashMap<UUID, ItemStack[]> playerInventories = new HashMap<>();
    private final HashMap<UUID, BossBar> bossBars = new HashMap<>();
    private final Location jailLocation;
    private final File jailedPlayersFile;
    private final FileConfiguration jailedPlayersConfig;

    public JailManager() {
        String worldName = main.getInstance().getConfig().getString("jail.world");
        World world = Bukkit.getWorld(worldName);
        int x = main.getInstance().getConfig().getInt("jail.x");
        int y = main.getInstance().getConfig().getInt("jail.y");
        int z = main.getInstance().getConfig().getInt("jail.z");

        if (world == null) {
            throw new IllegalStateException("The configured jail world is not loaded.");
        }

        this.jailLocation = new Location(world, x, y, z);

        jailedPlayersFile = new File(main.getInstance().getDataFolder(), "jailedPlayers.yml");
        if (!jailedPlayersFile.exists()) {
            try {
                jailedPlayersFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        jailedPlayersConfig = YamlConfiguration.loadConfiguration(jailedPlayersFile);
    }

    public void jailPlayer(Player player) {
        UUID playerId = player.getUniqueId();

        savePlayerState(player);

        player.teleport(jailLocation);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mute " + player.getName());

        BossBar bossBar = Bukkit.createBossBar("§cYou are in jail. Await further instructions.", BarColor.RED, BarStyle.SOLID);
        bossBar.addPlayer(player);
        bossBars.put(playerId, bossBar);

        player.sendMessage("§cYou have been jailed.");
        saveToConfig(playerId, true);

        Bukkit.broadcastMessage(ChatColor.RED + "Player " + player.getName() + " has been jailed!");

        applyEffects(player);
    }

    public void unjailPlayer(Player player) {
        UUID playerId = player.getUniqueId();

        if (isJailed(player)) {

            restorePlayerState(player);

            BossBar bossBar = bossBars.get(playerId);
            if (bossBar != null) {
                bossBar.removeAll();
                bossBars.remove(playerId);
            }

            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "unmute " + player.getName());

            player.sendMessage("§aYou have been released from jail.");
            saveToConfig(playerId, false);

            Bukkit.broadcastMessage(ChatColor.GREEN + "Player " + player.getName() + " has been released from jail!");

            removeEffects(player);

        } else {
            player.sendMessage("§cYou are not jailed.");
        }
    }

    public boolean isJailed(Player player) {
        return jailedPlayersConfig.getBoolean(player.getUniqueId().toString(), false);
    }

    private void savePlayerState(Player player) {
        UUID playerId = player.getUniqueId();

        playerInventories.put(playerId, player.getInventory().getContents());
        player.getInventory().clear();
    }

    private void restorePlayerState(Player player) {
        UUID playerId = player.getUniqueId();

        if (playerInventories.containsKey(playerId)) {
            player.getInventory().setContents(playerInventories.get(playerId));
            playerInventories.remove(playerId);
        }
    }

    private void saveToConfig(UUID playerId, boolean isJailed) {
        if (isJailed) {
            jailedPlayersConfig.set(playerId.toString(), true);
        } else {
            jailedPlayersConfig.set(playerId.toString(), null);
        }
        saveConfig();
    }

    private void saveConfig() {
        try {
            jailedPlayersConfig.save(jailedPlayersFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void applyEffects(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, Integer.MAX_VALUE, 1, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, Integer.MAX_VALUE, 1, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, Integer.MAX_VALUE, 1, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA, Integer.MAX_VALUE, 1, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE, Integer.MAX_VALUE, 1, false, false));
    }

    private void removeEffects(Player player) {
        player.removePotionEffect(PotionEffectType.BLINDNESS);
        player.removePotionEffect(PotionEffectType.SLOWNESS);
        player.removePotionEffect(PotionEffectType.WEAKNESS);
        player.removePotionEffect(PotionEffectType.NAUSEA);
        player.removePotionEffect(PotionEffectType.MINING_FATIGUE);
    }

}
