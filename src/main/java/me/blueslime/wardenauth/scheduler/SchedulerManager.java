package me.blueslime.wardenauth.scheduler;

import dev.mruniverse.slimelib.SlimePlatform;
import me.blueslime.wardenauth.scheduler.runnable.SlimeRunnable;

import java.util.concurrent.TimeUnit;

public interface SchedulerManager {

    static <T> SchedulerManager fromPlatform(SlimePlatform platform, T plugin) {
        switch (platform) {
            default:
            case BUNGEECORD:
                return new BungeeScheduler(plugin);
            case VELOCITY:
                return new VelocityScheduler(plugin);
            case SPIGOT:
                return new SpigotScheduler(plugin);
        }
    }

    SlimeScheduledTask schedule(SlimeRunnable runnable, long delay, long period, TimeUnit unit);

    SlimeScheduledTask schedule(SlimeRunnable runnable, long delay, TimeUnit unit);
}
