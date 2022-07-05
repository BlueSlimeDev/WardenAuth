package dev.justjustin.wardenauth.channels.spigot;

import dev.justjustin.wardenauth.SlimeFile;
import dev.justjustin.wardenauth.WardenAuth;
import dev.justjustin.wardenauth.channels.ChannelHandler;
import dev.mruniverse.slimelib.commands.sender.Sender;
import dev.mruniverse.slimelib.commands.sender.player.SlimePlayer;
import dev.mruniverse.slimelib.control.Control;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.Messenger;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SpigotChannel implements ChannelHandler, Listener {

    private final WardenAuth<JavaPlugin> plugin;

    private String verificationChannel;

    private String premiumChannel;

    private String offlineChannel;

    @SuppressWarnings("unchecked")
    public <T> SpigotChannel(WardenAuth<T> plugin) {
        this.plugin = (WardenAuth<JavaPlugin>) plugin;

        this.plugin.getPlugin().getServer().getPluginManager().registerEvents(
                this,
                this.plugin.getPlugin()
        );
    }

    @Override
    public void send(Channel channel, Sender sender) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        DataOutputStream dataStream = new DataOutputStream(outputStream);

        try {
            dataStream.writeUTF(
                    plugin.getLoader().getFiles().getControl(SlimeFile.SETTINGS).getString(
                            "settings." + channel.getId() + ".done-value",
                            "wa:" + channel.getId()
                    )
            );
            dataStream.writeUTF(sender.getName());
        } catch (IOException exception) {
            plugin.getLogs().error("Can't send plugin messages", exception);
        }

        SlimePlayer player = (SlimePlayer)sender;

        player.get().sendPluginMessage(
                plugin.getPlugin(),
                "wa:" + getChannel(channel),
                outputStream.toByteArray()
        );

    }

    @Override
    public void register() {
        Control settings = plugin.getLoader().getFiles().getControl(SlimeFile.SETTINGS);

        Messenger messenger = plugin.getPlugin().getServer().getMessenger();

        plugin.getLogs().info("Registering outgoing verification channel..");

        premiumChannel = settings.getString("settings.premium.channel", "wa967c2ax7");
        offlineChannel = settings.getString("settings.offline.channel", "wa881c7ax6");
        verificationChannel = settings.getString("settings.verification.channel", "wa381c6ax4");

        messenger.registerOutgoingPluginChannel(
                plugin.getPlugin(),
                "BungeeCord"
        );

        messenger.registerOutgoingPluginChannel(
                plugin.getPlugin(),
                "wa:" + verificationChannel
        );

        plugin.getLogs().info("Channel registered.");

        plugin.getLogs().info("Registering outgoing premium channel..");

        messenger.registerOutgoingPluginChannel(
                plugin.getPlugin(),
                "wa:" + premiumChannel
        );

        plugin.getLogs().info("Channel registered.");

        plugin.getLogs().info("Registering outgoing offline channel..");

        messenger.registerOutgoingPluginChannel(
                plugin.getPlugin(),
                "wa:" + offlineChannel
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
