package dev.spacety.jam;

import dev.spacety.jam.commands.JamCommand;
import org.bukkit.plugin.java.JavaPlugin;
import dev.spacety.jam.gui.listeners.GUIListener;

public class JAM extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info(this.getName() + " has been enabled!");
        getCommand("jam").setExecutor(new JamCommand());
        getServer().getPluginManager().registerEvents(new GUIListener(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info(this.getName() + " has been disabled!");
    }
}