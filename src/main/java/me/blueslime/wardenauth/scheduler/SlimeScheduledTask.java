package me.blueslime.wardenauth.scheduler;

import me.blueslime.wardenauth.scheduler.tasks.BungeeScheduledTask;
import me.blueslime.wardenauth.scheduler.tasks.SpigotScheduledTask;
import me.blueslime.wardenauth.scheduler.tasks.VelocityScheduledTask;

public abstract class SlimeScheduledTask {

    private final Runnable task;
    private final int id;

    protected SlimeScheduledTask(Runnable task, int id) {
        this.task = task;
        this.id = id;
    }


    public int getId() {
        return this.id;
    }

    public abstract Object getPlugin();

    public Runnable getTask() {
        return this.task;
    }

    public void cancel() {

    }

    public BungeeScheduledTask asBungeeTask() {
        return (BungeeScheduledTask)this;
    }

    public VelocityScheduledTask asVelocityTask() {
        return (VelocityScheduledTask)this;
    }

    public SpigotScheduledTask asSpigotTask() {
        return (SpigotScheduledTask)this;
    }
}
