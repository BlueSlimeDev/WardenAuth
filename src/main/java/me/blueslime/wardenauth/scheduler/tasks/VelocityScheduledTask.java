package me.blueslime.wardenauth.scheduler.tasks;

import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.scheduler.ScheduledTask;
import com.velocitypowered.api.scheduler.TaskStatus;
import me.blueslime.wardenauth.initialization.VelocityAuth;
import me.blueslime.wardenauth.scheduler.SlimeScheduledTask;

public class VelocityScheduledTask extends SlimeScheduledTask implements ScheduledTask {
    private final ProxyServer plugin;

    private final ScheduledTask original;

    public VelocityScheduledTask(ProxyServer plugin, ScheduledTask original, Runnable task, int id) {
        super(task, id);
        this.plugin = plugin;
        this.original = original;
    }

    @Override
    public ProxyServer getPlugin() {
        return plugin;
    }

    @Override
    public Object plugin() {
        return VelocityAuth.getMainInstance();
    }

    @Override
    public TaskStatus status() {
        return original.status();
    }

    @Override
    public void cancel() {
        original.cancel();
    }
}
