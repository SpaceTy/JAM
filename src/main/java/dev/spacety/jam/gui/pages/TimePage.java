package dev.spacety.jam.gui.pages;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import dev.spacety.jam.JAM;
import dev.spacety.jam.handlers.ServerSettingsHandler;

public class TimePage extends GUIPage {
    public TimePage(Player player) {
        super(player, JAM.getString("gui.time"));
        if (!JAM.config.getBoolean("features.server-settings.time-page.enabled")) {
            player.sendMessage(JAM.getString("messages.feature-coming-soon").replace("{feature}", "Time Control"));
            player.closeInventory();
            return;
        }
    }

    @Override
    protected void initializeItems() {
        addItem(11, Material.SUNFLOWER, 
            JAM.config.getString("features.server-settings.time-page.day.name", "§eSet Day"),
            JAM.config.getStringList("features.server-settings.time-page.day.lore").toArray(new String[0]));
        
        addItem(13, Material.CLOCK,
            JAM.config.getString("features.server-settings.time-page.noon.name", "§6Set Noon"),
            JAM.config.getStringList("features.server-settings.time-page.noon.lore").toArray(new String[0]));
        
        addItem(15, Material.ENDER_EYE,
            JAM.config.getString("features.server-settings.time-page.night.name", "§8Set Night"),
            JAM.config.getStringList("features.server-settings.time-page.night.lore").toArray(new String[0]));
        
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

        if (!JAM.getBoolean("features.server-settings.time-control")) {
            return;
        }

        switch (clickedItem.getType()) {
            case SUNFLOWER: // Day (6000 ticks)
                ServerSettingsHandler.setTime(player, player.getWorld(), 6000);
                break;
            case CLOCK: // Noon (12000 ticks)
                ServerSettingsHandler.setTime(player, player.getWorld(), 12000);
                break;
            case ENDER_EYE: // Night (18000 ticks)
                ServerSettingsHandler.setTime(player, player.getWorld(), 18000);
                break;
            default:
                break;
        }
    }
}