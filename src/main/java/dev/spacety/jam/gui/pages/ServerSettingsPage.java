package dev.spacety.jam.gui.pages;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import dev.spacety.jam.JAM;

public class ServerSettingsPage extends GUIPage {
    public ServerSettingsPage(Player player) {
        super(player, JAM.getString("gui.server-settings"));
    }

    @Override
    protected void initializeItems() {
        addItem(11, Material.CLOCK, "§eTime Control", "§7Manage server time");
        addItem(13, Material.WATER_BUCKET, "§bWeather Control", "§7Manage weather");
        addItem(15, Material.BEDROCK, "§cDifficulty", "§7Change server difficulty");
        addItem(26, Material.valueOf(JAM.getString("items.back-button.material")), 
                JAM.getString("items.back-button.name"), 
                JAM.getString("items.back-button.lore"));
    }
}