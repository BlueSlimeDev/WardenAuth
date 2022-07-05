package me.blueslime.wardenauth.channels;

import me.blueslime.wardenauth.WardenAuth;
import me.blueslime.wardenauth.channels.bungee.BungeeChannel;
import me.blueslime.wardenauth.channels.spigot.SpigotChannel;
import me.blueslime.wardenauth.channels.velocity.VelocityChannel;
import dev.mruniverse.slimelib.SlimePlatform;
import dev.mruniverse.slimelib.commands.sender.Sender;

public interface ChannelHandler {

    void send(Channel channel, Sender sender);

    void register();

    String getChannel(Channel channel);

    static <T> ChannelHandler fromPlatform(SlimePlatform platform, WardenAuth<T> plugin) {
        switch (platform) {
            case SPIGOT:
                return new SpigotChannel(plugin);
            default:
            case BUNGEECORD:
                return new BungeeChannel(plugin);
            case VELOCITY:
                return new VelocityChannel(plugin);
        }
    }

    enum Channel {
        VERIFICATION,
        PREMIUM,
        OFFLINE;

        public String getId() {
            return toString().toLowerCase();
        }

    }

}
