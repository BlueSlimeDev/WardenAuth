package me.blueslime.wardenauth.cache.list;

import me.blueslime.wardenauth.SlimeFile;
import me.blueslime.wardenauth.WardenAuth;
import me.blueslime.wardenauth.cache.AuthPlayer;
import me.blueslime.wardenauth.cache.PluginCache;
import me.blueslime.wardenauth.utils.TimerTools;

public class RequestCache extends PluginCache<AuthPlayer> {

    private TimerTools timer;

    public RequestCache(WardenAuth<?> plugin) {
        super(plugin);
    }

    @Override
    public void onLoad() {
        timer = TimerTools.fromText(
                getPlugin().getLoader().getFiles().getControl(SlimeFile.SETTINGS).getString("settings.timers.cache-time", "[MIN]20")
        );
    }

    @Override
    public boolean addCondition(AuthPlayer pending) {
        if (getCache().isEmpty()) {
            return true;
        }
        for (AuthPlayer player : getCache()) {
            if (player.getName().equals(pending.getName())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean removeCondition(AuthPlayer pending) {
        if (getCache().isEmpty()) {
            return false;
        }
        for (AuthPlayer player : getCache()) {
            if (player.getName().equals(pending.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clearCache() {
        getPlugin().getSchedulerManager().schedule(
                () -> getCache().removeIf(
                        player -> !getPlugin().getPlayers().existPlayer(player.getName())
                ),
                timer.getTime(),
                timer.getUnit()
        );
    }
}
