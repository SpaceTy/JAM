package dev.spacety.jam.gui.pages;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ServerSettingsPage extends GUIPage {
    public ServerSettingsPage(Player player) {
        super(player, "§8JAM Admin Panel - Server Settings");
    }

    @Override
    protected void initializeItems() {
        addItem(11, Material.CLOCK, "§eTime Control", "§7Manage server time");
        addItem(13, Material.WATER_BUCKET, "§bWeather Control", "§7Manage weather");
        addItem(15, Material.BEDROCK, "§cDifficulty", "§7Change server difficulty");
        addItem(26, Material.ARROW, "§7Back to Main Menu", "§7Click to return");
    }
}