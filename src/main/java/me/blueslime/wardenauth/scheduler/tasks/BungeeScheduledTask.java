package me.blueslime.wardenauth.scheduler.tasks;

import me.blueslime.wardenauth.scheduler.SlimeScheduledTask;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.scheduler.ScheduledTask;

public class BungeeScheduledTask extends SlimeScheduledTask implements ScheduledTask {

    private final Plugin plugin;
    private final ScheduledTask original;

    public BungeeScheduledTask(Plugin plugin, Runnable task, ScheduledTask original, int id) {
        super(task, id);
        this.plugin = plugin;
        this.original = original;
    }

    @Override
    public Plugin getPlugin() {
        return plugin;
    }

    @Override
    public Plugin getOwner() {
        return original.getOwner();
    }

    @Override
    public void cancel() {
        original.cancel();
    }
}
