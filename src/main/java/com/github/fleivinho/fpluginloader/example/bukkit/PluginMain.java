package com.github.fleivinho.fpluginloader.example.bukkit;

import com.github.fleivinho.fpluginloader.platform.bukkit.BukkitPlugin;
import com.github.fleivinho.fpluginloader.platform.bukkit.LoaderInfo;
import lombok.Getter;

public class PluginMain extends BukkitPlugin {

    @Getter
    private BukkitPlugin instance;

    public PluginMain() {
        super(new LoaderInfo("FPluginLoader", true));
    }

    @Override
    public void load() {
        instance = this;
    }

    @Override
    public void enable() {
        // Carregará todas as classes Commands e Listener dentro da package:
        loadPackage("com.github.fleivinho.fpluginloader.example.bukkit.commands");

        instance.getLogger().info("Plugin inicializado com sucesso!");
    }

    @Override
    public void disable() {
        instance.getLogger().info("Plugin desligado com sucesso!");
    }
}
