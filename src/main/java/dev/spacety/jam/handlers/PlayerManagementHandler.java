package dev.spacety.jam.handlers;

import dev.spacety.jam.JAM;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.ArrayList;
import java.util.List;

public class PlayerManagementHandler {
    public static void kickPlayer(Player admin, Player target, String reason) {
        if (target == null) return;
        if (!admin.hasPermission("jam.admin.kick")) {
            admin.sendMessage(JAM.getString("messages.no-permission"));
            return;
        }
        Bukkit.getServer().dispatchCommand(admin, "kick " + target.getName() + " " + reason);
        admin.sendMessage("§aPlayer " + target.getName() + " has been kicked.");
    }

    public static void banPlayer(Player admin, Player target, String reason) {
        if (target == null) return;
        if (!admin.hasPermission("jam.admin.ban")) {
            admin.sendMessage(JAM.getString("messages.no-permission"));
            return;
        }
        Bukkit.getServer().dispatchCommand(admin, "ban " + target.getName() + " " + reason);
        admin.sendMessage("§cPlayer " + target.getName() + " has been banned.");
    }

    public static Inventory getPlayerListInventory(Player admin) {
        if (!admin.hasPermission("jam.admin.list")) {
            admin.sendMessage(JAM.getString("messages.no-permission"));
            return null;
        }

        Inventory inv = Bukkit.createInventory(null, 54, "§8Online Players");
        List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());

        for (int i = 0; i < Math.min(players.size(), 45); i++) {
            Player player = players.get(i);
            ItemStack head = new ItemStack(Material.PLAYER_HEAD);
            ItemMeta meta = head.getItemMeta();
            meta.setDisplayName("§e" + player.getName());
            List<String> lore = new ArrayList<>();
            lore.add("§7Health: §c" + (int)player.getHealth() + "/" + (int)player.getAttribute(Attribute.MAX_HEALTH).getValue());
            lore.add("§7Gamemode: §b" + player.getGameMode().toString());
            lore.add("§7World: §a" + player.getWorld().getName());
            lore.add("");
            lore.add("§eLeft-click to teleport");
            lore.add("§cRight-click to kick");
            meta.setLore(lore);
            head.setItemMeta(meta);
            inv.setItem(i, head);
        }

        return inv;
    }
}