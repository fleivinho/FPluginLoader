package com.github.fleivinho.fpluginloader.platform.bukkit;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class BukkitPlugin extends JavaPlugin {

    @Getter
    private final LoaderInfo loaderInfo;

    public BukkitPlugin(LoaderInfo loaderInfo) {
        this.loaderInfo = loaderInfo;
    }

    public abstract void load();
    public abstract void enable();
    public abstract void disable();

    public void onLoad() {
        load();
    }

    public void onEnable() {
        if (getResource("config.yml") != null) saveDefaultConfig();

        if (loaderInfo.isCheckPremium()) {
            getLogger().info("Checando plugin pela API...");
            BukkitLicense.LicenseResponse licenseResponse = BukkitLicense.getResponse(this);
            if (licenseResponse.isShutdown()) {
                getLogger().warning("Plugin não validado!");
                Bukkit.getPluginManager().disablePlugin(this);
                return;
            }
            getLogger().info("Resposta da API: " + licenseResponse.name());
        }
        enable();
    }

    public void onDisable() {
        disable();
    }

    public void loadPackage(String... packageName) {

    }


}
