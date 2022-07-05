package dev.justjustin.wardenauth.commands.bungee;

import dev.justjustin.wardenauth.SlimeFile;
import dev.justjustin.wardenauth.WardenAuth;
import dev.mruniverse.slimelib.commands.command.Command;
import dev.mruniverse.slimelib.commands.command.SlimeCommand;
import dev.mruniverse.slimelib.commands.sender.Sender;
import dev.mruniverse.slimelib.control.Control;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

@Command(
        description = "Register Command of the plugin",
        shortDescription = "Register Command"
)
public class RegisterCommand implements SlimeCommand {

    private final WardenAuth<Plugin> plugin;

    private final List<String> aliases = new ArrayList<>();

    private final String command;

    @SuppressWarnings("unchecked")
    public <T> RegisterCommand(WardenAuth<T> plugin) {
        this.plugin = (WardenAuth<Plugin>) plugin;

        Control control = plugin.getLoader().getFiles().getControl(SlimeFile.COMMANDS);

        this.command = control.getString("commands.register.command", "register");

        this.aliases.addAll(
                control.getStringList("commands.register.aliases")
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
