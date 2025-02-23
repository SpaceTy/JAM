package dev.spacety.jam.gui.pages;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import dev.spacety.jam.JAM;
import dev.spacety.jam.util.PlayerMessage;
import net.kyori.adventure.audience.Audience;

public class KickConfirmPage extends GUIPage {
    private Player targetPlayer;

    public KickConfirmPage(Player player) {
        super(player, JAM.getString("gui.kick-confirm"));
    }

    public KickConfirmPage(Player player, Player targetPlayer) {
        super(player, JAM.getString("gui.kick-confirm"));
        this.targetPlayer = targetPlayer;
    }

    @Override
    protected void initializeItems() {
        addItem(11, Material.LIME_WOOL,
            JAM.config.getString("features.player-management.kick-page.confirm.name"),
            JAM.config.getStringList("features.player-management.kick-page.confirm.lore").toArray(new String[0]));
        
        addItem(15, Material.RED_WOOL,
            JAM.config.getString("features.player-management.kick-page.cancel.name"),
            JAM.config.getStringList("features.player-management.kick-page.cancel.lore").toArray(new String[0]));

        addItem(26, Material.valueOf(JAM.getString("items.back-button.material")),
                JAM.getString("items.back-button.name"),
                JAM.getString("items.back-button.lore"));
    }

    @Override
    public void handleClick(ItemStack clickedItem) {
        if (clickedItem.getType() == Material.ARROW) {
            player.openInventory(new KickPlayerPage(player).getInventory());
            return;
        }

        switch (clickedItem.getType()) {
            case LIME_WOOL:
                if (JAM.getBoolean("features.player-management.kick")) {
                    if (targetPlayer != null && targetPlayer.isOnline()) {
                        String kickMessage = JAM.config.getString("messages.kick-message");
                        targetPlayer.kickPlayer(kickMessage);
                        PlayerMessage.send((Audience) player, JAM.getString("messages.player-kicked")
                            .replace("{player}", targetPlayer.getName()));
                    }
                    player.openInventory(new PlayerManagementPage(player).getInventory());
                }
                break;
            case RED_WOOL:
                player.openInventory(new KickPlayerPage(player).getInventory());
                break;
            default:
                break;
        }
    }
}