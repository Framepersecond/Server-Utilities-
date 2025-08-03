// Made By Davtd with "Love"

package API;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.*;

import main.main;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigsAPI {

    public FileConfiguration permsconfig;
    private static ConfigsAPI instance;

    static {
        ConfigsAPI.instance = new ConfigsAPI();
    }

    public static ConfigsAPI getInstance() {
        return ConfigsAPI.instance;
    }

    public void setup() throws IOException {
        final File conf = new File(main.getPlugin().getDataFolder(), "permissions.yml");
        if (!conf.exists()) {
            try {
                conf.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                Bukkit.getConsoleSender().sendMessage("Error: could't create permissions.yml file!");
                return;
            }
        }
        // TODO later only copy on first creation
        this.copyConfig(main.getPlugin().getResource("permissions.yml"), conf);
        this.permsconfig = YamlConfiguration.loadConfiguration(conf);
    }

    protected void copyConfig(final InputStream i, final File config) {
        try {
            final OutputStream out = new FileOutputStream(config);
            final byte[] buf = new byte[710];
            int len;
            while ((len = i.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            i.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getPermsConfig() {
        return permsconfig;
    }
}
