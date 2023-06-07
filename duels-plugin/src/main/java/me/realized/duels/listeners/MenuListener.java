package me.realized.duels.listeners;

import me.realized.duels.DuelsPlugin;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
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
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
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
        if(droppedItem.getItemMeta() == null)return;
        if(!droppedItem.getItemMeta().hasDisplayName())return;
        if(droppedItem.getItemMeta().getDisplayName().equals(Objects.requireNonNull(menuItem.getItemMeta()).getDisplayName())) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    void on(PlayerInteractEvent event) {
        if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            ItemStack item = event.getItem();
            if(item == null)return;
            if(item.getItemMeta() == null)return;
            if(!item.getItemMeta().hasDisplayName())return;
            if(item.getItemMeta().getDisplayName().equals(Objects.requireNonNull(menuItem.getItemMeta()).getDisplayName())) {
//            DuelsPlugin.getInstance().getQueueUIManager().showUI(event.getPlayer());
                DuelsPlugin.getInstance().getQueueManager().getGui().open(event.getPlayer());
            }
        }
    }
}
