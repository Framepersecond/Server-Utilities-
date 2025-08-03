package listener;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Bat;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import main.main;

import java.util.Random;

public class batkilleffect implements Listener {

    @EventHandler
    public void DeathEffect(PlayerDeathEvent event) {
        Player player = event.getEntity();
        for (int i = 0; i <=20; i++) {
            Bat bat = (Bat) player.getWorld().spawnEntity(player.getLocation(), EntityType.BAT);
            ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
            Random random = new Random();
            int randomIndex = random.nextInt(2);
            bat.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 200, 1, true));
            bat.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS,200, 1, true));
            armorStand.setSmall(true);
            armorStand.setVisible(false);
            armorStand.setCustomNameVisible(true);
            bat.setPassenger(armorStand);
            if (randomIndex == 0) {
                armorStand.setCustomName(ChatColor.GOLD+"gg");
            } else {
                armorStand.setCustomName(ChatColor.YELLOW+"ez");
            }
            new BukkitRunnable() {
                @Override
                public void run() {
                    bat.remove();
                    armorStand.remove();
                }
            }.runTaskLater(main.getInstance(), 30L);
        }
        player.getWorld().playEffect(player.getLocation(), Effect.MOBSPAWNER_FLAMES,0);

    }
}
