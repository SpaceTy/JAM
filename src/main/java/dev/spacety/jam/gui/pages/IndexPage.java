package dev.spacety.jam.gui.pages;

import org.bukkit.Material;
import org.bukkit.entity.Player;

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
}