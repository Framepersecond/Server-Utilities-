package managers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.HashSet;
import java.util.Set;

public class MuteManager {
    private static final Set<String> mutedPlayers = new HashSet<>();

    public static void mutePlayer(String playerName) {
        mutedPlayers.add(playerName.toLowerCase());
    }

    public static void unmutePlayer(String playerName) {
        mutedPlayers.remove(playerName.toLowerCase());
    }

    public static boolean isPlayerMuted(String playerName) {
        return mutedPlayers.contains(playerName.toLowerCase());
    }

    public static Set<String> getMutedPlayers() {
        return new HashSet<>(mutedPlayers);
    }

    public static void printMutedPlayers() {
        if (mutedPlayers.isEmpty()) {
            Bukkit.getLogger().info(ChatColor.RED + "No players are currently muted.");
        } else {
            Bukkit.getLogger().info(ChatColor.GREEN + "Muted players: " + String.join(", ", mutedPlayers));
        }
    }
}

