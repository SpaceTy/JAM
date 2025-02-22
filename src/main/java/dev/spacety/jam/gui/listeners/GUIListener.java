package dev.spacety.jam.gui.listeners;

import dev.spacety.jam.gui.pages.*;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import dev.spacety.jam.util.PlayerMessage;
import net.kyori.adventure.audience.Audience;

public class GUIListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        String title = event.getView().getTitle();
        if (!title.startsWith("§8JAM Admin Panel")) {
            return;
        }

        event.setCancelled(true);
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();

        if (clickedItem == null || clickedItem.getType() == Material.GRAY_STAINED_GLASS_PANE) {
            return;
        }

        // Main Menu Navigation
        if (title.equals("§8JAM Admin Panel - Main Menu")) {
            handleMainMenu(player, clickedItem);
        }
        // Player Management Navigation
        else if (title.equals("§8JAM Admin Panel - Player Management")) {
            handlePlayerManagement(player, clickedItem);
        }
        // Self Management Navigation
        else if (title.equals("§8JAM Admin Panel - Self Management")) {
            handleSelfManagement(player, clickedItem);
        }
        // Server Settings Navigation
        else if (title.equals("§8JAM Admin Panel - Server Settings")) {
            handleServerSettings(player, clickedItem);
        }
    }

    private void handleMainMenu(Player player, ItemStack clickedItem) {
        Material type = clickedItem.getType();
        if (type == Material.PLAYER_HEAD) {
            player.openInventory(new PlayerManagementPage(player).getInventory());
        } else if (type == Material.ARMOR_STAND) {
            player.openInventory(new SelfPage(player).getInventory());
        } else if (type == Material.REDSTONE) {
            player.openInventory(new ServerSettingsPage(player).getInventory());
        }
    }

    private void handlePlayerManagement(Player player, ItemStack clickedItem) {
        if (clickedItem.getType() == Material.ARROW) {
            player.openInventory(new IndexPage(player).getInventory());
            return;
        }

        switch (clickedItem.getType()) {
            case DIAMOND_SWORD:
                PlayerMessage.send((Audience) player, "&eKick player feature coming soon!");
                break;
            case BARRIER:
                PlayerMessage.send((Audience) player, "&cBan player feature coming soon!");
                break;
            case PAPER:
                PlayerMessage.send((Audience) player, "&aPlayer list feature coming soon!");
                break;
        }
    }

    private void handleSelfManagement(Player player, ItemStack clickedItem) {
        if (clickedItem.getType() == Material.ARROW) {
            player.openInventory(new IndexPage(player).getInventory());
            return;
        }

        switch (clickedItem.getType()) {
            case COMPASS:
                PlayerMessage.send((Audience) player, "&eTeleport feature coming soon!");
                break;
            case GOLDEN_APPLE:
                player.setHealth(player.getMaxHealth());
                player.setFoodLevel(20);
                PlayerMessage.send((Audience) player, "&aYou have been healed!");
                break;
            case FEATHER:
                boolean flying = !player.getAllowFlight();
                player.setAllowFlight(flying);
                player.setFlying(flying);
                PlayerMessage.send((Audience) player, flying ? "&bFlight mode enabled!" : "&7Flight mode disabled!");
                break;
        }
    }

    private void handleServerSettings(Player player, ItemStack clickedItem) {
        if (clickedItem.getType() == Material.ARROW) {
            player.openInventory(new IndexPage(player).getInventory());
            return;
        }

        switch (clickedItem.getType()) {
            case CLOCK:
                PlayerMessage.send((Audience) player, "&eTime control feature coming soon!");
                break;
            case WATER_BUCKET:
                PlayerMessage.send((Audience) player, "&bWeather control feature coming soon!");
                break;
            case BEDROCK:
                PlayerMessage.send((Audience) player, "&cDifficulty control feature coming soon!");
                break;
        }
    }
}