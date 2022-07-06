package me.blueslime.wardenauth.scheduler;

import me.blueslime.wardenauth.scheduler.runnable.SlimeRunnable;
import me.blueslime.wardenauth.scheduler.tasks.BungeeScheduledTask;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.scheduler.ScheduledTask;

import java.util.concurrent.TimeUnit;

public class BungeeScheduler implements SchedulerManager {

    private final Plugin plugin;

    public <T> BungeeScheduler(T plugin) {
        this.plugin = (Plugin)plugin;
    }

    @Override
    public SlimeScheduledTask schedule(SlimeRunnable runnable, long delay, long period, TimeUnit unit) {
        ScheduledTask task = plugin.getProxy().getScheduler().schedule(
                plugin,
                runnable,
                delay,
                period,
                unit
        );

        return new BungeeScheduledTask(
                plugin,
                runnable,
                task,
                task.getId()
        );
    }

    @Override
    public SlimeScheduledTask schedule(SlimeRunnable runnable, long delay, TimeUnit unit) {
        ScheduledTask task = plugin.getProxy().getScheduler().schedule(
                plugin,
                runnable,
                delay,
                unit
        );

        return new BungeeScheduledTask(
                plugin,
                runnable,
                task,
                task.getId()
        );
    }
}
