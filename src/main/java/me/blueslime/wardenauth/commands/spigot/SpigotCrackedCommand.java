package me.blueslime.wardenauth.commands.spigot;

import me.blueslime.wardenauth.SlimeFile;
import me.blueslime.wardenauth.WardenAuth;
import dev.mruniverse.slimelib.commands.command.Command;
import dev.mruniverse.slimelib.commands.command.SlimeCommand;
import dev.mruniverse.slimelib.commands.sender.Sender;
import dev.mruniverse.slimelib.control.Control;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

@Command(
        description = "Cracked Command of the plugin",
        shortDescription = "Cracked Command"
)
public class SpigotCrackedCommand implements SlimeCommand {

    private final WardenAuth<JavaPlugin> plugin;

    private final List<String> aliases = new ArrayList<>();

    private final String command;

    @SuppressWarnings("unchecked")
    public <T> SpigotCrackedCommand(WardenAuth<T> plugin) {
        this.plugin = (WardenAuth<JavaPlugin>) plugin;

        Control control = plugin.getLoader().getFiles().getControl(SlimeFile.COMMANDS);

        this.command = control.getString("commands.offline.command", "cracked");

        this.aliases.addAll(
                control.getStringList("commands.offline.aliases")
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
