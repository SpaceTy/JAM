package dev.spacety.jam.gui.pages;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import dev.spacety.jam.JAM;

public class IndexPage extends GUIPage {
    public IndexPage(Player player) {
        super(player, "§8JAM Admin Panel - Main Menu");
    }

    @Override
    protected void initializeItems() {
        addItem(11, Material.PLAYER_HEAD, "§ePlayer Management", "§7Click to manage players");
        addItem(13, Material.ARMOR_STAND, "§bSelf Management", "§7Click to manage your settings");
        addItem(15, Material.REDSTONE, "§cServer Settings", "§7Click to change server settings");
    }

    @Override
    public void handleClick(ItemStack clickedItem) {
        if (!JAM.getBoolean("features.player-management.enabled") && 
            !JAM.getBoolean("features.self-management.enabled") && 
            !JAM.getBoolean("features.server-settings.enabled")) {
            return;
        }

        Material type = clickedItem.getType();
        switch (type) {
            case PLAYER_HEAD:
                if (JAM.getBoolean("features.player-management.enabled")) {
                    player.openInventory(new PlayerManagementPage(player).getInventory());
                }
                break;
            case ARMOR_STAND:
                if (JAM.getBoolean("features.self-management.enabled")) {
                    player.openInventory(new SelfPage(player).getInventory());
                }
                break;
            case REDSTONE:
                if (JAM.getBoolean("features.server-settings.enabled")) {
                    player.openInventory(new ServerSettingsPage(player).getInventory());
                }
                break;
            default:
                break;
        }
    }
}