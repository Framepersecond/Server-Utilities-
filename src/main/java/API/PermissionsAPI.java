// Made By Davtd with "Love"

package API;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import main.main;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class PermissionsAPI {

    // don't ever use a new instance of this class while break perms in the following hashmap
    HashMap<UUID, PermissionAttachment> permsmap = new HashMap<>();

    private static PermissionsAPI instance;

    private static ConfigsAPI configsAPI;

    static {
        PermissionsAPI.instance = new PermissionsAPI();
        PermissionsAPI.configsAPI = ConfigsAPI.getInstance();
    }
    

    public static PermissionsAPI getInstance() {
        return PermissionsAPI.instance;
    }

    public List<String> getGroupPerms(String group) {
        return configsAPI.getPermsConfig().getStringList(group);
    }

    public String getPlayersGroup(Player player) {
        String group = configsAPI.getPermsConfig().getString(player.getName());
        String defaultgroup = configsAPI.getPermsConfig().getString("default-group");
        if (group != null) {
            return group;
        }
        return defaultgroup;
    }

    public void givePlayersGroupPerms(Player player) {
        String group = this.getPlayersGroup(player);
        List<String> perms = this.getGroupPerms(group);;
        PermissionAttachment attachment = player.addAttachment(main.getPlugin());
        permsmap.put(player.getUniqueId(), attachment);
        if (perms != null) {
            for (String perm : perms) {
                PermissionAttachment pperms = permsmap.get(player.getUniqueId());
                pperms.setPermission(perm, true);
            }
        }
    }
}

// Dankiiiii
