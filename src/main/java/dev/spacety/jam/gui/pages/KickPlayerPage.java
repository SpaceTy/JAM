package dev.spacety.jam.gui.pages;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import dev.spacety.jam.JAM;

import java.util.ArrayList;
import java.util.List;

public class KickPlayerPage extends GUIPage {
    private Player targetPlayer;

    public KickPlayerPage(Player player) {
        super(player, JAM.getString("gui.kick-player"));
        if (!JAM.config.getBoolean("features.player-management.kick-page.enabled")) {
            player.sendMessage(JAM.getString("messages.feature-coming-soon").replace("{feature}", "Kick Player"));
            player.closeInventory();
            return;
        }
    }

    public KickPlayerPage(Player player, Player targetPlayer) {
        super(player, JAM.getString("gui.kick-player"));
        this.targetPlayer = targetPlayer;
    }

    @Override
    protected void initializeItems() {
        if (targetPlayer == null) {
            // Display online players
            int slot = 0;
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                if (onlinePlayer.equals(player)) continue;
                
                ItemStack head = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta meta = (SkullMeta) head.getItemMeta();
                meta.setOwningPlayer(onlinePlayer);
                meta.setDisplayName(JAM.config.getString("features.player-management.kick-page.player-head.name")
                    .replace("{player}", onlinePlayer.getName()));
                
                List<String> lore = new ArrayList<>();
                for (String loreLine : JAM.config.getStringList("features.player-management.kick-page.player-head.lore")) {
                    lore.add(loreLine.replace("{player}", onlinePlayer.getName()));
                }
                meta.setLore(lore);
                head.setItemMeta(meta);
                
                addItemStack(slot++, head);
            }
        } else {
            // Display target player info
            ItemStack head = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta meta = (SkullMeta) head.getItemMeta();
            meta.setOwningPlayer(targetPlayer);
            meta.setDisplayName(JAM.config.getString("features.player-management.kick-page.player-head.name")
                .replace("{player}", targetPlayer.getName()));
            
            List<String> lore = new ArrayList<>();
            for (String loreLine : JAM.config.getStringList("features.player-management.kick-page.player-head.lore")) {
                lore.add(loreLine.replace("{player}", targetPlayer.getName()));
            }
            meta.setLore(lore);
            head.setItemMeta(meta);
            
            addItemStack(13, head);
        }

        addItem(26, Material.valueOf(JAM.getString("items.back-button.material")),
                JAM.getString("items.back-button.name"),
                JAM.getString("items.back-button.lore"));
    }

    @Override
    public void handleClick(ItemStack clickedItem) {
        if (clickedItem.getType() == Material.ARROW) {
            player.openInventory(new PlayerManagementPage(player).getInventory());
            return;
        }

        if (targetPlayer == null) {
            if (clickedItem.getType() == Material.PLAYER_HEAD) {
                SkullMeta meta = (SkullMeta) clickedItem.getItemMeta();
                if (meta.getOwningPlayer() != null) {
                    Player target = Bukkit.getPlayer(meta.getOwningPlayer().getUniqueId());
                    if (target != null && target.isOnline()) {
                        player.openInventory(new KickPlayerPage(player, target).getInventory());
                    }
                }
            }
        } else {
            if (clickedItem.getType() == Material.PLAYER_HEAD) {
                player.openInventory(new KickConfirmPage(player, targetPlayer).getInventory());
            }
        }
    }
}