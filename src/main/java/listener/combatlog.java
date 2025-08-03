package listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import main.main;

import java.util.HashMap;
import java.util.UUID;

public class combatlog implements Listener {

    private HashMap<UUID, Integer> combatlist;
    private HashMap<UUID, BossBar> bossBars;

    public combatlog() {
        this.combatlist = new HashMap<>();
        this.bossBars = new HashMap<>();

        new BukkitRunnable() {
            @Override
            public void run() {
                onInterval();
            }
        }.runTaskTimer(main.getInstance(), 0, 20);
    }

    @EventHandler
    public void onDamager(EntityDamageByEntityEvent event) 
            
    {
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            Player target = (Player) event.getEntity();
            Player damager = (Player) event.getDamager();
            

            addPlayerToCombat(target);
            addPlayerToCombat(damager);

            if (!combatlist.containsKey(target.getUniqueId())) {
                target.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You are now in Combat against: " + ChatColor.DARK_AQUA + damager.getDisplayName() + "!");
            }
            if (!combatlist.containsKey(damager.getUniqueId())) {
                damager.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You are now in Combat against: " + ChatColor.DARK_AQUA + target.getDisplayName() + "!");
            }
        }
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        UUID playerId = player.getUniqueId();

        String[] restrictedCommands = {"/tp", "/clan home", "/home", "/gm 1", "/gamemode", "/back", "/tpaccept", "tpdeny", "/tpa", "tpo", "afk"};

        if (combatlist.containsKey(playerId)) {
            for (String command : restrictedCommands) {
                if (event.getMessage().toLowerCase().startsWith(command)) {
                    event.setCancelled(true);
                    player.sendMessage(ChatColor.RED + "You cannot use this command while in combat!");
                    return;
                }
            }
        }
    }

    public void onInterval() {
        HashMap<UUID, Integer> temp = new HashMap<>();
        for (UUID id : combatlist.keySet()) {
            int timer = combatlist.get(id) - 1;

            if (timer > 0) {
                temp.put(id, timer);
                
                if (bossBars.containsKey(id)) {
                    BossBar bar = bossBars.get(id);
                    bar.setProgress(timer / 10.0);
                }
            } else {

                Player player = Bukkit.getPlayer(id);
                if (player != null) {
                    removePlayerFromCombat(player);
                }
            }
        }
        combatlist = temp;
    }

    @EventHandler
    public void onDisconnect(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        UUID playerId = player.getUniqueId();

        if (combatlist.containsKey(playerId)) {
            player.setHealth(0.0);

            Bukkit.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + player.getDisplayName() + " has logged out during combat and was slain!");
        }

        combatlist.remove(playerId);


        if (bossBars.containsKey(playerId)) {
            BossBar bar = bossBars.get(playerId);
            bar.removeAll();
            bossBars.remove(playerId);
        }
    }

    private void addPlayerToCombat(Player player) {
        UUID playerId = player.getUniqueId();
        combatlist.put(playerId, 10);

        BossBar bar;
        if (bossBars.containsKey(playerId)) {
            bar = bossBars.get(playerId);
        } else {
            bar = Bukkit.createBossBar(ChatColor.RED + "Combat Timer", BarColor.RED, BarStyle.SOLID);
            bossBars.put(playerId, bar);
            bar.addPlayer(player);
        }

        bar.setProgress(1.0);

        player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 20 * 11, 1, false, false));

    }

    private void removePlayerFromCombat(Player player) {
        UUID playerId = player.getUniqueId();

        combatlist.remove(playerId);

        if (bossBars.containsKey(playerId)) {
            BossBar bar = bossBars.get(playerId);
            bar.removeAll();
            bossBars.remove(playerId);
        }

        player.removePotionEffect(PotionEffectType.GLOWING);
    }
    
}
