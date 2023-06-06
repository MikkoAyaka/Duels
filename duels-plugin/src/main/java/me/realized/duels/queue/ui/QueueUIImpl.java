package me.realized.duels.queue.ui;

import me.realized.duels.api.kit.Kit;
import me.realized.duels.api.queue.DQueue;
import me.realized.duels.api.queue.ui.QueueUI;
import me.realized.duels.config.Lang;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class QueueUIImpl implements QueueUI {

    Lang lang;
    Inventory menu;
    public QueueUIImpl(Lang lang, List<DQueue> queues) {
        this.lang = lang;
        menu = Bukkit.createInventory(null, 54, lang.getMessage("GUI.queue-menu.title"));
        for(int i = 0;i < queues.size();i++) {
            DQueue queue = queues.get(i);
            Kit kit = queue.getKit();
            if(kit == null) continue;
            ItemStack is = kit.getDisplayed();
            menu.setItem(i,is);
        }
    }
    @Override
    public void show(Player player) {
        player.openInventory(menu);
    }
}
