package dev.justjustin.wardenauth.commands.spigot;

import dev.justjustin.wardenauth.SlimeFile;
import dev.justjustin.wardenauth.WardenAuth;
import dev.mruniverse.slimelib.commands.command.Command;
import dev.mruniverse.slimelib.commands.command.SlimeCommand;
import dev.mruniverse.slimelib.commands.sender.Sender;
import dev.mruniverse.slimelib.control.Control;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

@Command(
        description = "Change Spawn Location Command of the plugin",
        shortDescription = "Plugin Spawn Command"
)
public class SpigotSpawnCommand implements SlimeCommand {

    private final WardenAuth<JavaPlugin> plugin;

    private final List<String> aliases = new ArrayList<>();

    private final String command;

    @SuppressWarnings("unchecked")
    public <T> SpigotSpawnCommand(WardenAuth<T> plugin) {
        this.plugin = (WardenAuth<JavaPlugin>) plugin;

        Control control = plugin.getLoader().getFiles().getControl(SlimeFile.COMMANDS);

        this.command = control.getString("commands.spawn.command", "changepassword");

        this.aliases.addAll(
                control.getStringList("commands.spawn.aliases")
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
