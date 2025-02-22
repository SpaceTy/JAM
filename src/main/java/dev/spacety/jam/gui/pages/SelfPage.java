package dev.spacety.jam.gui.pages;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class SelfPage extends GUIPage {
    public SelfPage(Player player) {
        super(player, "§8JAM Admin Panel - Self Management");
    }

    @Override
    protected void initializeItems() {
        addItem(11, Material.COMPASS, "§eTeleport", "§7Teleport to a location");
        addItem(13, Material.GOLDEN_APPLE, "§aHeal", "§7Restore your health");
        addItem(15, Material.FEATHER, "§bFly", "§7Toggle flight mode");
        addItem(26, Material.ARROW, "§7Back to Main Menu", "§7Click to return");
    }
}