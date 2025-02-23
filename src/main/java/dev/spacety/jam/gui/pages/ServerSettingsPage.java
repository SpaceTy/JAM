package dev.spacety.jam.gui.pages;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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

    @Override
    public void handleClick(ItemStack clickedItem) {
        if (clickedItem.getType() == Material.ARROW) {
            player.openInventory(new IndexPage(player).getInventory());
            return;
        }

        switch (clickedItem.getType()) {
            case CLOCK:
                if (JAM.getBoolean("features.server-settings.time-control")) {
                    player.openInventory(new TimePage(player).getInventory());
                }
                break;
            case WATER_BUCKET:
                if (JAM.getBoolean("features.server-settings.weather-control")) {
                    player.openInventory(new WeatherPage(player).getInventory());
                }
                break;
            case BEDROCK:
                if (JAM.getBoolean("features.server-settings.difficulty-control")) {
                    player.openInventory(new DifficultyPage(player).getInventory());
                }
                break;
            default:
                break;
        }
    }
}