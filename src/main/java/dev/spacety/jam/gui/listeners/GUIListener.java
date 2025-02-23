package dev.spacety.jam.gui.listeners;

import dev.spacety.jam.gui.pages.*;
import dev.spacety.jam.JAM;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

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

        GUIPage page = null;
        if (title.equals(JAM.getString("gui.main-menu"))) {
            page = new IndexPage(player);
        }
        else if (title.equals(JAM.getString("gui.player-management"))) {
            page = new PlayerManagementPage(player);
        }
        else if (title.equals(JAM.getString("gui.kick-player"))) {
            page = new KickPlayerPage(player);
        }
        else if (title.equals(JAM.getString("gui.kick-confirm"))) {
            page = new KickConfirmPage(player);
        }
        else if (title.equals(JAM.getString("gui.self-management"))) {
            page = new SelfPage(player);
        }
        else if (title.equals(JAM.getString("gui.time"))) {
            page = new TimePage(player);
        }
        else if (title.equals(JAM.getString("gui.weather"))) {
            page = new WeatherPage(player);
        }
        else if (title.equals(JAM.getString("gui.server-settings"))) {
            page = new ServerSettingsPage(player);
        }
        else if (title.equals(JAM.getString("gui.difficulty-selection"))) {
            page = new DifficultyPage(player);
        }

        if (page != null) {
            page.handleClick(clickedItem);
        }
    }
}