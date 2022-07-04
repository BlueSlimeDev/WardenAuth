package dev.justjustin.wardenauth;

import dev.mruniverse.slimelib.SlimePlatform;
import dev.mruniverse.slimelib.SlimePlugin;
import dev.mruniverse.slimelib.SlimePluginInformation;
import dev.mruniverse.slimelib.input.InputManager;
import dev.mruniverse.slimelib.loader.BaseSlimeLoader;
import dev.mruniverse.slimelib.loader.DefaultSlimeLoader;
import dev.mruniverse.slimelib.logs.SlimeLogger;
import dev.mruniverse.slimelib.logs.SlimeLogs;

import java.io.File;

public final class WardenAuth<T> implements SlimePlugin<T> {

    private final BaseSlimeLoader<T> baseSlimeLoader;
    private final SlimePluginInformation information;
    private final SlimePlatform platform;
    private final SlimeLogs logs;
    private final File folder;
    private final T plugin;

    public WardenAuth(File dataFolder, T plugin) {
        this.plugin = plugin;

        this.platform = SlimePlatform.getAutomatically();

        this.logs = SlimeLogger.createLogs(
                this,
                "WardenAuth"
        );

        this.folder = dataFolder;

        this.information = new SlimePluginInformation(
                platform,
                plugin
        );

        this.baseSlimeLoader = new DefaultSlimeLoader<>(
                this,
                InputManager.create(
                        platform,
                        plugin
                )
        );

        getLoader().setFiles(SlimeFile.class);

        getLoader().init();
    }

    @Override
    public SlimePluginInformation getPluginInformation() {
        return information;
    }

    @Override
    public SlimePlatform getServerType() {
        return platform;
    }

    @Override
    public SlimeLogs getLogs() {
        return logs;
    }

    @Override
    public BaseSlimeLoader<T> getLoader() {
        return baseSlimeLoader;
    }

    public T getPlugin() {
        return plugin;
    }

    @Override
    public void reload() {

    }

    @Override
    public File getDataFolder() {
        return folder;
    }
}
