package me.realized.duels.command.commands.queue.subcommands;

import me.realized.duels.DuelsPlugin;
import me.realized.duels.Permissions;
import me.realized.duels.command.BaseCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UICommand extends BaseCommand {
    public UICommand(final DuelsPlugin plugin) {
        super(plugin, "ui", null, null, Permissions.QUEUE, 1, true, "u");
    }
    @Override
    protected void execute(CommandSender sender, String label, String[] args) {
//        queueUIManager.showUI((Player) sender);
    }
}
