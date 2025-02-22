package dev.spacety.jam.gui.pages;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class PlayerManagementPage extends GUIPage {
    public PlayerManagementPage(Player player) {
        super(player, "§8JAM Admin Panel - Player Management");
    }

    @Override
    protected void initializeItems() {
        addItem(11, Material.DIAMOND_SWORD, "§eKick Player", "§7Click to kick a player");
        addItem(13, Material.BARRIER, "§cBan Player", "§7Click to ban a player");
        addItem(15, Material.PAPER, "§aPlayer List", "§7Click to view online players");
        addItem(26, Material.ARROW, "§7Back to Main Menu", "§7Click to return");
    }
}