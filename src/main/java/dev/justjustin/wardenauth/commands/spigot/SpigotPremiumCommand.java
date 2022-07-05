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
        description = "Premium Command of the plugin in spigot",
        shortDescription = "Spigot Premium Command"
)
public class SpigotPremiumCommand implements SlimeCommand {

    private final WardenAuth<JavaPlugin> plugin;

    private final List<String> aliases = new ArrayList<>();

    private final String command;

    @SuppressWarnings("unchecked")
    public <T> SpigotPremiumCommand(WardenAuth<T> plugin) {
        this.plugin = (WardenAuth<JavaPlugin>) plugin;

        Control control = plugin.getLoader().getFiles().getControl(SlimeFile.COMMANDS);

        this.command = control.getString("commands.premium.command", "premiummode");

        this.aliases.addAll(
                control.getStringList("commands.premium.aliases")
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