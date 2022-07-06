package me.blueslime.wardenauth.cache;

import me.blueslime.wardenauth.WardenAuth;

import java.util.ArrayList;
import java.util.List;

public abstract class PluginCache<T> {

    private final List<T> cache = new ArrayList<>();

    private final WardenAuth<?> plugin;

    public PluginCache(WardenAuth<?> plugin) {
        this.plugin = plugin;
        onLoad();
    }

    public abstract void onLoad();

    public boolean addCondition(T pending) {
        return true;
    }

    public boolean removeCondition(T pending) {
        return true;
    }

    public abstract void clearCache();

    public void add(T t) {
        if (addCondition(t)) {
            this.cache.add(t);
        }
    }

    public void remove(T t) {
        if (removeCondition(t)) {
            this.cache.remove(t);
        }
    }

    public List<T> getCache() {
        return cache;
    }

    public WardenAuth<?> getPlugin() {
        return plugin;
    }
}
