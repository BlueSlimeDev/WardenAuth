package me.blueslime.wardenauth.commands.bungee;

import me.blueslime.wardenauth.SlimeFile;
import me.blueslime.wardenauth.WardenAuth;
import dev.mruniverse.slimelib.commands.command.Command;
import dev.mruniverse.slimelib.commands.command.SlimeCommand;
import dev.mruniverse.slimelib.commands.sender.Sender;
import dev.mruniverse.slimelib.control.Control;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

@Command(
        description = "Logout Command of the plugin",
        shortDescription = "Logout Command"
)
public class LogoutCommand implements SlimeCommand {

    private final WardenAuth<Plugin> plugin;

    private final List<String> aliases = new ArrayList<>();

    private final String command;

    @SuppressWarnings("unchecked")
    public <T> LogoutCommand(WardenAuth<T> plugin) {
        this.plugin = (WardenAuth<Plugin>) plugin;

        Control control = plugin.getLoader().getFiles().getControl(SlimeFile.COMMANDS);

        this.command = control.getString("commands.logout.command", "logout");

        this.aliases.addAll(
                control.getStringList("commands.logout.aliases")
        );

    }

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    public List<String> getAliases() {
        return aliases;
    }


    @Override
    public void execute(Sender sender, String commandLabel, String[] args) {
        //TODO: Command Execute
    }
}
