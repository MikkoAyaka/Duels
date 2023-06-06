package me.realized.duels.queue.ui;

import me.realized.duels.DuelsPlugin;
import me.realized.duels.api.queue.ui.QueueUI;
import me.realized.duels.api.queue.ui.QueueUIManager;
import me.realized.duels.queue.QueueManager;
import org.bukkit.entity.Player;

public class QueueUIManagerImpl implements QueueUIManager {
    private final DuelsPlugin plugin;
    private final QueueManager queueManager;
    private final QueueUI queueUI;

    public QueueUIManagerImpl(final DuelsPlugin plugin) {
        this.plugin = plugin;
        this.queueManager = plugin.getQueueManager();
        this.queueUI = new QueueUIImpl(plugin.getLang(),queueManager.getQueues());
    }
    @Override
    public void showUI(Player player) {
        queueUI.show(player);
    }
}
