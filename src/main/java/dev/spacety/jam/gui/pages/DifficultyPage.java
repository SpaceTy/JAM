package dev.spacety.jam.gui.pages;

import org.bukkit.Difficulty;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import dev.spacety.jam.JAM;
import dev.spacety.jam.handlers.ServerSettingsHandler;

public class DifficultyPage extends GUIPage {
    public DifficultyPage(Player player) {
        super(player, JAM.getString("gui.difficulty-selection"));
        if (!JAM.config.getBoolean("features.server-settings.difficulty-page.enabled")) {
            player.sendMessage(JAM.getString("messages.feature-coming-soon").replace("{feature}", "Difficulty"));
            player.closeInventory();
            return;
        }
    }

    @Override
    public void handleClick(ItemStack clickedItem) {
        if (clickedItem.getType() == Material.ARROW) {
            player.openInventory(new ServerSettingsPage(player).getInventory());
            return;
        }
        switch (clickedItem.getType()) {
            case GRASS_BLOCK:
                if (JAM.getBoolean("features.server-settings.difficulty-control")) {
                    ServerSettingsHandler.setDifficulty(player, player.getWorld(), Difficulty.PEACEFUL);
                }
                break;
            case WOODEN_SWORD:
                if (JAM.getBoolean("features.server-settings.difficulty-control")) {
                    ServerSettingsHandler.setDifficulty(player, player.getWorld(), Difficulty.EASY);
                }
                break;
            case IRON_SWORD:
                if (JAM.getBoolean("features.server-settings.difficulty-control")) {
                    ServerSettingsHandler.setDifficulty(player, player.getWorld(), Difficulty.NORMAL);
                }
                break;
            case DIAMOND_SWORD:
                if (JAM.getBoolean("features.server-settings.difficulty-control")) {
                    ServerSettingsHandler.setDifficulty(player, player.getWorld(), Difficulty.HARD);
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void initializeItems() {
        addItem(10, Material.GRASS_BLOCK, 
            JAM.config.getString("features.server-settings.difficulty-page.peaceful.name"),
            JAM.config.getStringList("features.server-settings.difficulty-page.peaceful.lore").toArray(new String[0]));
        
        addItem(12, Material.WOODEN_SWORD,
            JAM.config.getString("features.server-settings.difficulty-page.easy.name"),
            JAM.config.getStringList("features.server-settings.difficulty-page.easy.lore").toArray(new String[0]));
        
        addItem(14, Material.IRON_SWORD,
            JAM.config.getString("features.server-settings.difficulty-page.normal.name"),
            JAM.config.getStringList("features.server-settings.difficulty-page.normal.lore").toArray(new String[0]));
        
        addItem(16, Material.DIAMOND_SWORD,
            JAM.config.getString("features.server-settings.difficulty-page.hard.name"),
            JAM.config.getStringList("features.server-settings.difficulty-page.hard.lore").toArray(new String[0]));
        
        addItem(26, Material.valueOf(JAM.getString("items.back-button.material")),
                JAM.getString("items.back-button.name"),
                JAM.getString("items.back-button.lore"));
    }
}