package me.blueslime.wardenauth.commands;

import me.blueslime.wardenauth.WardenAuth;
import me.blueslime.wardenauth.commands.bungee.*;
import me.blueslime.wardenauth.commands.spigot.SpigotCrackedCommand;
import me.blueslime.wardenauth.commands.spigot.SpigotSpawnCommand;
import dev.mruniverse.slimelib.commands.command.SlimeCommand;

import java.util.EnumMap;
import java.util.Map;

public class CommandManager {

    private final Map<Command, SlimeCommand> commandMap = new EnumMap<>(Command.class);

    private final WardenAuth<?> plugin;

    public <T> CommandManager(WardenAuth<T> plugin) {
        this.plugin = plugin;

        switch (plugin.getServerType()) {
            case SPIGOT:
            case SPONGE:
                commandMap.put(Command.PREMIUM, new SpigotCrackedCommand(plugin));
                commandMap.put(Command.SPAWN, new SpigotSpawnCommand(plugin));
                commandMap.put(Command.CRACKED, new SpigotCrackedCommand(plugin));
                break;
            case VELOCITY:
            case BUNGEECORD:
                commandMap.put(Command.ADMIN, new AdminCommand(plugin));
                commandMap.put(Command.CHANGE_PASSWORD, new ChangePasswordCommand(plugin));
                commandMap.put(Command.CRACKED, new CrackedCommand(plugin));
                commandMap.put(Command.LOGIN, new LoginCommand(plugin));
                commandMap.put(Command.LOGOUT, new LogoutCommand(plugin));
                commandMap.put(Command.PREMIUM, new PremiumCommand(plugin));
                commandMap.put(Command.REGISTER, new RegisterCommand(plugin));
                break;
        }
    }

    public void register() {
        for (SlimeCommand command : commandMap.values()) {
            plugin.getLoader().getCommands().register(
                    command
            );
        }
    }

    public SlimeCommand getCommand(Command command) {
        return commandMap.get(command);
    }

}
