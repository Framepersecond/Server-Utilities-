package main;


import API.ConfigsAPI;
import commands.*;
import commands.Home.HomeCommand;
import commands.Home.HomeFiles;
import commands.Home.SetHomeCommand;
import managers.JailManager;
import commands.TP.TpAcceptCommand;
import commands.TP.TpDenyCommand;
import commands.TP.TpaCommand;
import commands.TP.TpoCommand;
import managers.WarnManager;
import listener.*;
import listener.Home.PlayerListener;
import managers.BanManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.*;

public final class main extends JavaPlugin {

    private static main instance;
    private static Plugin plugin;
    public static boolean flytoggle = false;
    public static ConfigsAPI configsAPI = ConfigsAPI.getInstance();
    private static final Map<Player, Player> teleportRequests = new HashMap<>();
    BanManager banManager = new BanManager(getDataFolder());
    WarnManager warnManager = new WarnManager(getDataFolder());
    private HashMap<UUID, Location> homes;
    private HomeFiles files;
    private List<UUID> que;
    private static JailManager jailManager;



    @Override
    public void onEnable() {
        instance = this;
        plugin = this;

        // Initialize JailManager early
        jailManager = new JailManager();

        saveDefaultConfig();
        registerEvents();
        registerCommands();
        plugin.saveConfig();
        File dataFolder = getDataFolder();
        if (!dataFolder.exists()) dataFolder.mkdir();


        this.homes = new HashMap<>();
        this.files = new HomeFiles(this);
        this.files.init();
        this.que = new ArrayList<>();

        try {
            configsAPI.setup();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        getLogger().info("The Plugin Server has loaded");
    }


    public void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new batkilleffect(), this);
        pm.registerEvents(new join(), this);
        pm.registerEvents(new leave(), this);
        pm.registerEvents(new XPBottleParticles(), this);
        pm.registerEvents(new blockbreak(), this);
        pm.registerEvents(new MenuListeners(), this);
        pm.registerEvents(new combatlog(), this);
        pm.registerEvents(new Chat(), this);
        pm.registerEvents(new backpacklistener(), this);
        pm.registerEvents(new playtime(), this);
        pm.registerEvents(new BeaconNoSkyListener(getInstance()), this);
        pm.registerEvents(new blockbuild(), this);
        pm.registerEvents(new trashlistener(), this);
        pm.registerEvents(new lumber(), this);
        pm.registerEvents(new ServerguiListener(), this);
        pm.registerEvents(new PlayerListener(this), this);
        pm.registerEvents(new backlistener(), this);

    }

    public void registerCommands() {
        getCommand("break").setExecutor(new BlockBreakToggle());
        getCommand("trash").setExecutor(new trash());
        getCommand("backpack").setExecutor(new backpack());
        getCommand("colorcodes").setExecutor(new colorcodes());
        getCommand("build").setExecutor(new BlockBuildToggle());
        getCommand("lumber").setExecutor(new LumberCommand());
        getCommand("server").setExecutor(new ServerCommand(new ServerguiListener()));
        getCommand("rules").setExecutor(new RulesCommand());
        getCommand("msg").setExecutor(new MsgCommand());
        getCommand("tpa").setExecutor(new TpaCommand(this));
        getCommand("tpo").setExecutor(new TpoCommand());
        getCommand("tpaccept").setExecutor(new TpAcceptCommand(this));
        getCommand("tpdeny").setExecutor(new TpDenyCommand(this));
        getCommand("clear").setExecutor(new ClearInventoryCommand());
        getCommand("back").setExecutor(new BackCommand(this));

    }

    public static main getInstance() {
        return instance;
    }

    public static JailManager getJailManager() {
        return jailManager;
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    public static Map<Player, Player> getTeleportRequests() { return teleportRequests;}

    public void addHome(UUID id, Location location) {
        this.homes.put(id, location);

    }

    public Location getHome(UUID id) {
        return this.homes.get(id);
    }

    public Location setHome(UUID id, Location location) {
        return this.homes.put(id, location);

    }

    public boolean hasHome(UUID id) {

        return this.homes.containsKey(id);

    }
    public HashMap<UUID , Location> getHomes() {
        return this.homes;
    }
    public HomeFiles getFiles() {
        return this.files;
    }

    public void addQue(UUID id) {
        this.que.add(id);
    }
    public void cancelQue(UUID id) {
        this.que.remove(id);
    }
    public boolean isQued(UUID id) {
        return this.que.contains(id);
    }

}


