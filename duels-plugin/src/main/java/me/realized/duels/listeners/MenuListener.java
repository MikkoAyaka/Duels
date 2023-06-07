package me.realized.duels.listeners;

import me.realized.duels.DuelsPlugin;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MenuListener implements Listener {
    ItemStack menuItem;
    public MenuListener() {
        menuItem = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = menuItem.getItemMeta();
        if(meta == null)return;
        meta.setDisplayName("§8[ §a开始匹配 §8]");
        List<String> lores = new ArrayList<>();
        lores.add("§7");
        lores.add("§f右键 §7打开匹配菜单，");
        lores.add("§7点击对应模式进入匹配队列，");
        lores.add("§7再次点击可以取消匹配。");
        lores.add("§7");
        meta.setLore(lores);
        meta.setUnbreakable(true);
        menuItem.setItemMeta(meta);
    }
    @EventHandler
    void on(PlayerJoinEvent event) {
        event.getPlayer().getInventory().clear();
        event.getPlayer().getInventory().addItem(menuItem);
    }
    // prevent player drop menu item
    @EventHandler
    void on(PlayerDropItemEvent event) {
        ItemStack droppedItem = event.getItemDrop().getItemStack();
        if(droppedItem.getItemMeta() != null && droppedItem.getItemMeta().getDisplayName().equals(Objects.requireNonNull(menuItem.getItemMeta()).getDisplayName())) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    void on(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        if(item == null)return;
        if(item.getItemMeta() == null)return;
        if(item.getItemMeta().getDisplayName().equals(Objects.requireNonNull(menuItem.getItemMeta()).getDisplayName())) {
            DuelsPlugin.getInstance().getQueueUIManager().showUI(event.getPlayer());
        }
    }
    @EventHandler
    void on(InventoryMoveItemEvent event) {
        ItemStack droppedItem = event.getItem();
        if(droppedItem.getItemMeta() != null && droppedItem.getItemMeta().getDisplayName().equals(Objects.requireNonNull(menuItem.getItemMeta()).getDisplayName())) {
            event.setCancelled(true);
        }
    }
}
