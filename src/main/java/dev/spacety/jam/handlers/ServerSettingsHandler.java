package dev.spacety.jam.handlers;

import dev.spacety.jam.JAM;
import org.bukkit.World;
import org.bukkit.Difficulty;
import org.bukkit.entity.Player;

public class ServerSettingsHandler {
    public static void setTime(Player admin, World world, long time) {
        if (!admin.hasPermission("jam.admin.time")) {
            admin.sendMessage(JAM.getString("messages.no-permission"));
            return;
        }
        world.setTime(time);
        admin.sendMessage("§aTime has been set to " + time + " ticks.");
    }

    public static void setWeather(Player admin, World world, boolean storm) {
        if (!admin.hasPermission("jam.admin.weather")) {
            admin.sendMessage(JAM.getString("messages.no-permission"));
            return;
        }
        if (storm) {
            world.setStorm(true);
            world.setThundering(true);
            admin.sendMessage("§bWeather has been set to stormy.");
        } else {
            world.setStorm(false);
            world.setThundering(false);
            admin.sendMessage("§eWeather has been set to clear.");
        }
    }

    public static void setDifficulty(Player admin, World world, Difficulty difficulty) {
        if (!admin.hasPermission("jam.admin.difficulty")) {
            admin.sendMessage(JAM.getString("messages.no-permission"));
            return;
        }
        world.setDifficulty(difficulty);
        admin.sendMessage("§cDifficulty has been set to " + difficulty.toString().toLowerCase() + ".");
    }
}