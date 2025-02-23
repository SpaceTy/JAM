package dev.spacety.jam.gui.pages;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import dev.spacety.jam.JAM;
import dev.spacety.jam.util.PlayerMessage;
import net.kyori.adventure.audience.Audience;

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

    @Override
    public void handleClick(ItemStack clickedItem) {
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
}