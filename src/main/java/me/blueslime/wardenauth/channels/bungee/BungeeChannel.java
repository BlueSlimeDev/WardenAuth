package me.blueslime.wardenauth.channels.bungee;

import me.blueslime.wardenauth.SlimeFile;
import me.blueslime.wardenauth.WardenAuth;
import me.blueslime.wardenauth.channels.ChannelHandler;
import me.blueslime.wardenauth.commands.Command;
import dev.mruniverse.slimelib.commands.sender.Sender;
import dev.mruniverse.slimelib.commands.sender.player.SlimeProxiedPlayer;
import dev.mruniverse.slimelib.control.Control;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

import java.io.*;
import java.util.concurrent.TimeUnit;

public class BungeeChannel implements ChannelHandler, Listener {

    private final WardenAuth<Plugin> plugin;

    private String verificationChannel;

    private String premiumChannel;

    private String offlineChannel;

    @SuppressWarnings("unchecked")
    public <T> BungeeChannel(WardenAuth<T> plugin) {
        this.plugin = (WardenAuth<Plugin>) plugin;

        this.plugin.getPlugin().getProxy().getPluginManager().registerListener(
                this.plugin.getPlugin(),
                this
        );
    }

    @Override
    public void send(Channel channel, Sender sender) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        DataOutputStream dataStream = new DataOutputStream(outputStream);

        SlimeProxiedPlayer player = (SlimeProxiedPlayer) sender;

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

        player.get().getServer().sendData(
                "wa:" + getChannel(channel),
                outputStream.toByteArray()
        );

        plugin.getPlugin().getProxy().getScheduler().schedule(
                plugin.getPlugin(),
                () -> player.get().getServer().sendData(
                        "wa:" + getChannel(channel),
                        outputStream.toByteArray()
                ), 1L, TimeUnit.SECONDS
        );

    }

    @EventHandler
    public void onPluginMessage(PluginMessageEvent event) {
        String tag = event.getTag();

        String command;

        if (!tag.equals(premiumChannel) && !tag.equals(offlineChannel)) {
            return;
        }

        if (tag.equals(premiumChannel)) {
            command = plugin.getCommandManager().getCommand(Command.PREMIUM).getCommand();
        } else {
            command = plugin.getCommandManager().getCommand(Command.CRACKED).getCommand();
        }

        DataInputStream dataStream = new DataInputStream(
                new ByteArrayInputStream(
                        event.getData()
                )
        );

        try {
            String user = dataStream.readUTF();

            ProxiedPlayer player = plugin.getPlugin().getProxy().getPlayer(user);

            if (player != null) {
                plugin.getPlugin().getProxy().getPluginManager().dispatchCommand(
                        player,
                        command
                );
            }

        } catch (IOException exception) {
            plugin.getLogs().error("Can't read plugin-messages channel", exception);
        }

    }

    @Override
    public void register() {
        Control settings = plugin.getLoader().getFiles().getControl(SlimeFile.SETTINGS);

        ProxyServer server = plugin.getPlugin().getProxy();

        plugin.getLogs().info("Registering verification channel..");

        premiumChannel = settings.getString("settings.premium.channel", "wa967c2ax7");
        offlineChannel = settings.getString("settings.offline.channel", "wa881c7ax6");
        verificationChannel = settings.getString("settings.verification.channel", "wa381c6ax4");

        server.registerChannel(
                "wa:" + verificationChannel
        );

        plugin.getLogs().info("Channel registered.");

        plugin.getLogs().info("Registering premium channel..");

        server.registerChannel(
                "wa:" + premiumChannel
        );

        plugin.getLogs().info("Channel registered.");

        plugin.getLogs().info("Registering offline channel..");

        server.registerChannel(
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
