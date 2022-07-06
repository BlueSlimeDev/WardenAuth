package me.blueslime.wardenauth.scheduler.tasks;

import me.blueslime.wardenauth.scheduler.SlimeScheduledTask;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

public class SpigotScheduledTask extends SlimeScheduledTask implements BukkitTask {
    private final JavaPlugin plugin;
    private final BukkitTask original;

    public SpigotScheduledTask(JavaPlugin plugin, Runnable task, BukkitTask original, int id) {
        super(task, id);
        this.plugin = plugin;
        this.original = original;
    }

    @Override
    public JavaPlugin getPlugin() {
        return plugin;
    }

    @Override
    public int getTaskId() {
        return getId();
    }

    @NotNull
    @Override
    public Plugin getOwner() {
        return original.getOwner();
    }

    @Override
    public boolean isSync() {
        return original.isSync();
    }

    @Override
    public boolean isCancelled() {
        return original.isCancelled();
    }

    @Override
    public void cancel() {
        original.cancel();
    }
}
