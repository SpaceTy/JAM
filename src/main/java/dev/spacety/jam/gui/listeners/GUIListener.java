package dev.spacety.jam.gui.listeners;

import dev.spacety.jam.gui.pages.*;
import dev.spacety.jam.handlers.ServerSettingsHandler;
import dev.spacety.jam.JAM;

import org.bukkit.Difficulty;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
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
        if (!title.startsWith(JAM.getString("gui.main-menu").split(" - ")[0])) {
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

        if (title.equals(JAM.getString("gui.main-menu"))) {
            handleMainMenu(player, clickedItem);
        }
        else if (title.equals(JAM.getString("gui.player-management"))) {
            handlePlayerManagement(player, clickedItem);
        }
        else if (title.equals(JAM.getString("gui.self-management"))) {
            handleSelfManagement(player, clickedItem);
        }
        else if (title.equals(JAM.getString("gui.server-settings"))) {
            handleServerSettings(player, clickedItem);
        }
        else if (title.equals(JAM.getString("gui.difficulty-selection"))) {
            handleDifficultySelection(player, clickedItem);
        }
    }

    private void handleMainMenu(Player player, ItemStack clickedItem) {
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

    private void handlePlayerManagement(Player player, ItemStack clickedItem) {
        if (clickedItem.getType() == Material.ARROW) {
            player.openInventory(new IndexPage(player).getInventory());
            return;
        }

        switch (clickedItem.getType()) {
            case DIAMOND_SWORD:
                if (JAM.getBoolean("features.player-management.kick")) {
                    PlayerMessage.send((Audience) player, JAM.getString("messages.feature-coming-soon").replace("{feature}", "Kick player"));
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

    private void handleSelfManagement(Player player, ItemStack clickedItem) {
        if (clickedItem.getType() == Material.ARROW) {
            player.openInventory(new IndexPage(player).getInventory());
            return;
        }

        switch (clickedItem.getType()) {
            case COMPASS:
                if (JAM.getBoolean("features.self-management.teleport")) {
                    PlayerMessage.send((Audience) player, JAM.getString("messages.feature-coming-soon").replace("{feature}", "Teleport"));
                }
                break;
            case GOLDEN_APPLE:
                if (JAM.getBoolean("features.self-management.heal")) {
                    player.setHealth(player.getAttribute(Attribute.MAX_HEALTH).getValue());
                    player.setFoodLevel(20);
                    PlayerMessage.send((Audience) player, JAM.getString("messages.heal-success"));
                }
                break;
            case FEATHER:
                if (JAM.getBoolean("features.self-management.fly")) {
                    boolean flying = !player.getAllowFlight();
                    player.setAllowFlight(flying);
                    player.setFlying(flying);
                    PlayerMessage.send((Audience) player, flying ? JAM.getString("messages.flight-enabled") : JAM.getString("messages.flight-disabled"));
                }
                break;
            default:
                break;
        }
    }

    private void handleServerSettings(Player player, ItemStack clickedItem) {
        String title = player.getOpenInventory().getTitle();
        System.out.println(title);
        
        
        if (clickedItem.getType() == Material.ARROW) {
            player.openInventory(new IndexPage(player).getInventory());
            return;
        }

        switch (clickedItem.getType()) {
            case CLOCK:
                if (JAM.getBoolean("features.server-settings.time-control")) {
                    // Set time to day (6000 ticks)
                    ServerSettingsHandler.setTime(player, player.getWorld(), 6000);
                }
                break;
            case WATER_BUCKET:
                if (JAM.getBoolean("features.server-settings.weather-control")) {
                    // Toggle weather
                    ServerSettingsHandler.setWeather(player, player.getWorld(), !player.getWorld().hasStorm());
                }
                break;
            case BEDROCK:
                if (JAM.getBoolean("features.server-settings.difficulty-control")) {
                    player.openInventory(new DifficultyPage(player).getInventory());
                }
                break;
            default:
                break;
        }
    }
}