package me.blueslime.wardenauth.initialization;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import me.blueslime.wardenauth.WardenAuth;

import java.io.File;
import java.nio.file.Path;
import java.util.logging.Logger;

@SuppressWarnings("unused")
@Plugin(
        id = "wardenauth",
        name = "WardenAuth",
        version = "1.0.0-SNAPSHOT",
        description = "Auth Plugin for offline-mode servers, and auto-premium-mode for Premiums",
        url = "https://github.com/MrUniverse44/WardenAuth",
        authors = { "JustJustin" }
)
public class VelocityAuth {

    private WardenAuth<ProxyServer> instance;

    @Inject
    private ProxyServer server;

    @Inject
    @DataDirectory
    private Path dataDirectory;

    @Inject
    private Logger logger;

    @Subscribe
    public void onInitialize(ProxyInitializeEvent event) {
        instance = new WardenAuth<>(
                new File(dataDirectory.getParent().toFile(), "WardenAuth"),
                server
        );
    }

    public WardenAuth<ProxyServer> getInstance() {
        return instance;
    }
}
