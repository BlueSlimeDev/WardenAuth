package dev.justjustin.wardenauth.initialization;

import dev.justjustin.wardenauth.WardenAuth;
import net.md_5.bungee.api.plugin.Plugin;

@SuppressWarnings("unused")
public class BungeeAuth extends Plugin {

    private WardenAuth<Plugin> instance;

    @Override
    public void onEnable() {
        instance = new WardenAuth<>(
                getDataFolder(),
                this
        );
    }

    public WardenAuth<Plugin> getInstance() {
        return instance;
    }

}
