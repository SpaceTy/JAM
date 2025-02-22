package dev.spacety.jam;

import dev.spacety.jam.commands.JamCommand;
import org.bukkit.plugin.java.JavaPlugin;
import dev.spacety.jam.gui.listeners.GUIListener;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

public class JAM extends JavaPlugin {
    public static FileConfiguration config;

    @Override
    public void onEnable() {
        getLogger().info(this.getName() + " has been enabled!");
        getCommand("jam").setExecutor(new JamCommand());
        getServer().getPluginManager().registerEvents(new GUIListener(), this);
        loadCfg();
    }

    @Override
    public void onDisable() {
        getLogger().info(this.getName() + " has been disabled!");
    }

    public static String getString(String key) {
        return config.getString(key);
    }

    public static List<String> getStringList(String key) {
        return config.getStringList(key);
    }

    public static int getInt(String key) {
        return config.getInt(key);
    }

    public static boolean getBoolean(String key) {
        return config.getBoolean(key);
    }

    public static void reloadCfg() {
        JAM plugin = JAM.getPlugin(JAM.class);
        plugin.reloadConfig();
        config = plugin.getConfig();
    }

    public void loadCfg() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        saveDefaultConfig();
        config = getConfig();
    }
}