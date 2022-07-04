package dev.justjustin.wardenauth.initialization;

import dev.justjustin.wardenauth.WardenAuth;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class SpigotAuth extends JavaPlugin {

    private WardenAuth<JavaPlugin> instance;

    @Override
    public void onEnable() {
        instance = new WardenAuth<>(
                getDataFolder(),
                this
        );
    }

    public WardenAuth<JavaPlugin> getInstance() {
        return instance;
    }
}
