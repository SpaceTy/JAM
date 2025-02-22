package dev.spacety.jam;

import dev.spacety.jam.commands.JamCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class JAM extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info(this.getName() + " has been enabled!");
        getCommand("jam").setExecutor(new JamCommand());
    }

    @Override
    public void onDisable() {
        getLogger().info(this.getName() + " has been disabled!");
    }
}