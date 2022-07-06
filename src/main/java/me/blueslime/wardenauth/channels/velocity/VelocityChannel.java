package me.blueslime.wardenauth.channels.velocity;

import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import me.blueslime.wardenauth.SlimeFile;
import me.blueslime.wardenauth.WardenAuth;
import me.blueslime.wardenauth.channels.ChannelHandler;
import dev.mruniverse.slimelib.commands.sender.Sender;
import dev.mruniverse.slimelib.commands.sender.player.SlimeVelocityPlayer;
import dev.mruniverse.slimelib.control.Control;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class VelocityChannel implements ChannelHandler {

    private final Map<Channel, MinecraftChannelIdentifier> channelIdentifierMap = new EnumMap<>(Channel.class);

    private final WardenAuth<ProxyServer> plugin;

    private String verificationChannel;

    private String premiumChannel;

    private String offlineChannel;

    @SuppressWarnings("unchecked")
    public <T> VelocityChannel(WardenAuth<T> plugin) {
        this.plugin = (WardenAuth<ProxyServer>) plugin;
    }

    @Override
    public void send(Channel channel, Sender sender) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        DataOutputStream dataStream = new DataOutputStream(outputStream);

        SlimeVelocityPlayer player = (SlimeVelocityPlayer) sender;

        try {
            dataStream.writeUTF(
                    plugin.getLoader().getFiles().getControl(SlimeFile.SETTINGS).getString(
                            "settings." + channel.getId() + ".done-value",
                            "wa:" + channel.getId()
                    )
            );
        } catch (IOException exception) {
            plugin.getLogs().error("Can't send plugin messages", exception);
        }

        player.get().sendPluginMessage(
                channelIdentifierMap.get(channel),
                outputStream.toByteArray()
        );

        plugin.getSchedulerManager().schedule(
                () -> player.get().sendPluginMessage(
                        channelIdentifierMap.get(channel),
                        outputStream.toByteArray()
                ),
                1L,
                TimeUnit.SECONDS
        );
    }

    @Override
    public void register() {
        Control settings = plugin.getLoader().getFiles().getControl(SlimeFile.SETTINGS);

        ProxyServer server = plugin.getPlugin();

        plugin.getLogs().info("Registering verification channel..");

        premiumChannel = settings.getString("settings.premium.channel", "wa967c2ax7");
        offlineChannel = settings.getString("settings.offline.channel", "wa881c7ax6");
        verificationChannel = settings.getString("settings.verification.channel", "wa381c6ax4");

        channelIdentifierMap.put(
                Channel.PREMIUM,
                MinecraftChannelIdentifier.create(
                        "wa",
                        premiumChannel
                )
        );

        channelIdentifierMap.put(
                Channel.VERIFICATION,
                MinecraftChannelIdentifier.create(
                        "wa",
                        verificationChannel
                )
        );

        channelIdentifierMap.put(
                Channel.OFFLINE,
                MinecraftChannelIdentifier.create(
                        "wa",
                        offlineChannel
                )
        );

        server.getChannelRegistrar().register(
                channelIdentifierMap.get(Channel.VERIFICATION)
        );

        plugin.getLogs().info("Channel registered.");

        plugin.getLogs().info("Registering premium channel..");

        server.getChannelRegistrar().register(
                channelIdentifierMap.get(Channel.PREMIUM)
        );

        plugin.getLogs().info("Channel registered.");

        plugin.getLogs().info("Registering offline channel..");

        server.getChannelRegistrar().register(
                channelIdentifierMap.get(Channel.OFFLINE)
        );

        plugin.getLogs().info("Channel registered.");
    }

    @Override
    public String getChannel(Channel channel) {
        switch (channel) {
            case OFFLINE:
                return offlineChannel;
            case PREMIUM:
                return premiumChannel;
            default:
            case VERIFICATION:
                return verificationChannel;
        }
    }
}
