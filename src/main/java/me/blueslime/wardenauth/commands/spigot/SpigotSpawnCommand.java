package me.blueslime.wardenauth.commands.spigot;

import dev.mruniverse.slimelib.commands.sender.player.SlimePlayer;
import me.blueslime.wardenauth.SlimeFile;
import me.blueslime.wardenauth.WardenAuth;
import dev.mruniverse.slimelib.commands.command.Command;
import dev.mruniverse.slimelib.commands.command.SlimeCommand;
import dev.mruniverse.slimelib.commands.sender.Sender;
import dev.mruniverse.slimelib.control.Control;
import org.bukkit.Location;
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
        String permission = plugin.getLoader().getFiles().getControl(SlimeFile.COMMANDS).getString("commands.spawn.permission", "NONE");
        boolean hasPermission;

        if (permission.equalsIgnoreCase("NONE")) {
            hasPermission = true;
        } else {
            hasPermission = sender.hasPermission(
                    permission
            );
        }

        if (sender instanceof SlimePlayer && hasPermission) {

            Location location = ((SlimePlayer) sender).get().getLocation();

            Control settings = plugin.getLoader().getFiles().getControl(SlimeFile.COMMANDS);

            if (location.getWorld() == null) {
                return;
            }

            settings.set("commands.spawn.location.world", location.getWorld().getName());
            settings.set("commands.spawn.location.x", location.getX());
            settings.set("commands.spawn.location.y", location.getY());
            settings.set("commands.spawn.location.z", location.getZ());
            settings.set("commands.spawn.location.yaw", location.getYaw());
            settings.set("commands.spawn.location.pitch", location.getPitch());

            settings.save();

            sender.sendColoredMessage("&aAuth location has been set correctly.");

        }
    }
}
