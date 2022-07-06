package me.blueslime.wardenauth.scheduler;

import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.scheduler.ScheduledTask;
import com.velocitypowered.api.scheduler.Scheduler;
import me.blueslime.wardenauth.initialization.VelocityAuth;
import me.blueslime.wardenauth.scheduler.runnable.SlimeRunnable;
import me.blueslime.wardenauth.scheduler.tasks.VelocityScheduledTask;

import java.util.concurrent.TimeUnit;

public class VelocityScheduler implements SchedulerManager {

    private final ProxyServer plugin;

    public <T> VelocityScheduler(T plugin) {
        this.plugin = (ProxyServer)plugin;
    }

    @Override
    public SlimeScheduledTask schedule(SlimeRunnable runnable, long delay, long period, TimeUnit unit) {
        Scheduler.TaskBuilder task = plugin.getScheduler().buildTask(
                VelocityAuth.getMainInstance(),
                runnable
        );

        task.delay(delay, unit);

        task.repeat(period, unit);

        ScheduledTask scheduledTask = task.schedule();

        return new VelocityScheduledTask(
                plugin,
                scheduledTask,
                runnable,
                0
        );
    }

    @Override
    public SlimeScheduledTask schedule(SlimeRunnable runnable, long delay, TimeUnit unit) {
        Scheduler.TaskBuilder task = plugin.getScheduler().buildTask(
                VelocityAuth.getMainInstance(),
                runnable
        );

        task.delay(delay, unit);

        ScheduledTask scheduledTask = task.schedule();

        return new VelocityScheduledTask(
                plugin,
                scheduledTask,
                runnable,
                0
        );
    }
}
