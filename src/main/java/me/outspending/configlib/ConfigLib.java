package me.outspending.configlib;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.yaml.snakeyaml.Yaml;

public final class ConfigLib extends JavaPlugin {

    @Override
    public void onEnable() {
        ConfigLoader.constructClass(new Testing(), YamlConfiguration.class);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
