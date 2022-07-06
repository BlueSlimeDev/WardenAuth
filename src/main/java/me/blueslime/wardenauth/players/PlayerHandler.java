package me.blueslime.wardenauth.players;

import dev.mruniverse.slimelib.SlimePlatform;

import java.util.List;
import java.util.UUID;

public interface PlayerHandler {

    /**
     * Get names of all players
     * @return List(String) Players Names
     */
    List<String> getPlayersNames();

    /**
     * Get the name of a specified player
     * @return String Player Name
     */
    default String getPlayerName(int id) {
        List<String> names = getPlayersNames();

        if (names.size() >= id) {
            return names.get(id - 1);
        }
        return null;
    }

    boolean existPlayer(String text);

    boolean existPlayer(UUID uuid);

    /**
     * Get the amount of players online in the server
     * @return int Players Size
     */
    int getPlayersSize();

    /**
     * Get the amount of max players in the server
     * @return int Max players size
     */
    int getMaxPlayers();

    static <T> PlayerHandler fromPlatform(SlimePlatform platform, T plugin) {
        switch (platform) {
            case SPIGOT:
                return new SpigotPlayerHandler(plugin);
            case VELOCITY:
                return new VelocityPlayerHandler(plugin);
            default:
            case BUNGEECORD:
                return new BungeePlayerHandler(plugin);
        }
    }

}
