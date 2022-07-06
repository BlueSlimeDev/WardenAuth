package me.blueslime.wardenauth.scheduler;

import me.blueslime.wardenauth.scheduler.runnable.SlimeRunnable;
import me.blueslime.wardenauth.scheduler.tasks.SpigotScheduledTask;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.concurrent.TimeUnit;

public class SpigotScheduler implements SchedulerManager {

    private final JavaPlugin plugin;

    public <T> SpigotScheduler(T plugin) {
        this.plugin = (JavaPlugin)plugin;
    }

    @Override
    public SlimeScheduledTask schedule(SlimeRunnable runnable, long delay, long period, TimeUnit unit) {
        BukkitTask task = plugin.getServer().getScheduler().runTaskTimer(
                plugin,
                runnable,
                unit.toSeconds(delay),
                unit.toSeconds(period)
        );

        return new SpigotScheduledTask(
                plugin,
                runnable,
                task,
                task.getTaskId()
        );
    }

    @Override
    public SlimeScheduledTask schedule(SlimeRunnable runnable, long delay, TimeUnit unit) {
        BukkitTask task = plugin.getServer().getScheduler().runTaskLater(
                plugin,
                runnable,
                unit.toSeconds(delay)
        );

        return new SpigotScheduledTask(
                plugin,
                runnable,
                task,
                task.getTaskId()
        );
    }
}
