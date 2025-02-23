package dev.spacety.jam.gui.pages;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class GUIPage {
    protected final Player player;
    protected Inventory inventory;
    protected final String title;

    public GUIPage(Player player, String title) {
        this.player = player;
        this.title = title;
        this.inventory = Bukkit.createInventory(null, 27, title);
        initialize();
    }

    public abstract void handleClick(ItemStack clickedItem);

    protected void initialize() {
        fillWithGlass();
        initializeItems();
    }

    protected void fillWithGlass() {
        ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta glassMeta = glass.getItemMeta();
        if (glassMeta != null) {
            glassMeta.setDisplayName(" ");
            glass.setItemMeta(glassMeta);
        }
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, glass);
        }
    }

    protected void addItem(int slot, Material material, String name, String... lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            meta.setLore(java.util.Arrays.asList(lore));
            item.setItemMeta(meta);
        }
        inventory.setItem(slot, item);
    }

    protected void addItemStack(int slot, ItemStack item) {
        inventory.setItem(slot, item);
    }

    protected abstract void initializeItems();

    public void open() {
        player.openInventory(inventory);
    }

    public Inventory getInventory() {
        return inventory;
    }
}