package dev.spacety.jam.gui.pages;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import dev.spacety.jam.JAM;
import dev.spacety.jam.util.PlayerMessage;
import net.kyori.adventure.audience.Audience;

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

    @Override
    public void handleClick(ItemStack clickedItem) {
        if (clickedItem.getType() == Material.ARROW) {
            player.openInventory(new IndexPage(player).getInventory());
            return;
        }

        switch (clickedItem.getType()) {
            case DIAMOND_SWORD:
                if (JAM.getBoolean("features.player-management.kick")) {
                    player.openInventory(new KickPlayerPage(player).getInventory());
                }
                break;
            case BARRIER:
                if (JAM.getBoolean("features.player-management.ban")) {
                    PlayerMessage.send((Audience) player, JAM.getString("messages.feature-coming-soon").replace("{feature}", "Ban player"));
                }
                break;
            case PAPER:
                if (JAM.getBoolean("features.player-management.player-list")) {
                    PlayerMessage.send((Audience) player, JAM.getString("messages.feature-coming-soon").replace("{feature}", "Player list"));
                }
                break;
            default:
                break;
        }
    }
}