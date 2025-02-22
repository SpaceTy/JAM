package dev.spacety.jam.gui.pages;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import dev.spacety.jam.JAM;

public class SelfPage extends GUIPage {
    public SelfPage(Player player) {
        super(player, JAM.getString("gui.self-management"));
    }

    @Override
    protected void initializeItems() {
        addItem(11, Material.COMPASS, "§eTeleport", "§7Teleport to a location");
        addItem(13, Material.GOLDEN_APPLE, "§aHeal", "§7Restore your health");
        addItem(15, Material.FEATHER, "§bFly", "§7Toggle flight mode");
        addItem(26, Material.valueOf(JAM.getString("items.back-button.material")), 
                JAM.getString("items.back-button.name"), 
                JAM.getString("items.back-button.lore"));
    }
}