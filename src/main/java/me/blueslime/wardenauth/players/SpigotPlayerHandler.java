package me.blueslime.wardenauth.players;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SpigotPlayerHandler implements PlayerHandler {

    private final JavaPlugin plugin;

    public <T> SpigotPlayerHandler(T plugin) {
        this.plugin = (JavaPlugin) plugin;
    }

    @Override
    public List<String> getPlayersNames() {
        List<String> names = new ArrayList<>();

        int current = 1;
        int max = 10;

        for (Player player : plugin.getServer().getOnlinePlayers()) {
            if (current <= max) {
                names.add(player.getName());
            } else {
                return names;
            }
            current++;
        }

        return names;
    }

    public boolean existPlayer(String text) {
        return plugin.getServer().getPlayer(text) != null;
    }

    public boolean existPlayer(UUID uuid) {
        return plugin.getServer().getPlayer(uuid) != null;
    }

    @Override
    public int getPlayersSize() {
        return plugin.getServer().getOnlinePlayers().size();
    }

    @Override
    public int getMaxPlayers() {
        return plugin.getServer().getMaxPlayers();
    }
}