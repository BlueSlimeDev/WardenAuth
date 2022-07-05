package dev.justjustin.wardenauth.channels;

import dev.justjustin.wardenauth.WardenAuth;
import dev.justjustin.wardenauth.channels.bungee.BungeeChannel;
import dev.justjustin.wardenauth.channels.spigot.SpigotChannel;
import dev.justjustin.wardenauth.channels.velocity.VelocityChannel;
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
