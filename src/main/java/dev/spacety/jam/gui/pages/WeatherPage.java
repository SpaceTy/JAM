package dev.spacety.jam.gui.pages;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import dev.spacety.jam.JAM;
import dev.spacety.jam.handlers.ServerSettingsHandler;

public class WeatherPage extends GUIPage {
    public WeatherPage(Player player) {
        super(player, JAM.getString("gui.weather"));
        if (!JAM.config.getBoolean("features.server-settings.weather-page.enabled")) {
            player.sendMessage(JAM.getString("messages.feature-coming-soon").replace("{feature}", "Weather Control"));
            player.closeInventory();
            return;
        }
    }

    @Override
    protected void initializeItems() {
        addItem(11, Material.WATER_BUCKET,
            JAM.config.getString("features.server-settings.weather-page.rain.name", "§bToggle Rain"),
            JAM.config.getStringList("features.server-settings.weather-page.rain.lore").toArray(new String[0]));
        
        addItem(15, Material.SUNFLOWER,
            JAM.config.getString("features.server-settings.weather-page.clear.name", "§eClear Weather"),
            JAM.config.getStringList("features.server-settings.weather-page.clear.lore").toArray(new String[0]));
        
        addItem(26, Material.valueOf(JAM.getString("items.back-button.material")),
                JAM.getString("items.back-button.name"),
                JAM.getString("items.back-button.lore"));
    }

    @Override
    public void handleClick(ItemStack clickedItem) {
        if (clickedItem.getType() == Material.ARROW) {
            player.openInventory(new ServerSettingsPage(player).getInventory());
            return;
        }

        if (!JAM.getBoolean("features.server-settings.weather-control")) {
            return;
        }

        switch (clickedItem.getType()) {
            case WATER_BUCKET:
                ServerSettingsHandler.setWeather(player, player.getWorld(), true);
                break;
            case SUNFLOWER:
                ServerSettingsHandler.setWeather(player, player.getWorld(), false);
                break;
            default:
                break;
        }
    }
}