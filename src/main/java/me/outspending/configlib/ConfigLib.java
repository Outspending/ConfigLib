package me.outspending.configlib;

import me.outspending.configlib.examples.ConfigExample;
import me.outspending.configlib.files.YamlFile;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;

public final class ConfigLib extends JavaPlugin {

    @Override
    public void onEnable() {
        YamlFile file = new YamlFile(getDataFolder(), "config.yml");
        List<CachedConfigField<?>> cachedConfigFields = ConfigLoader.constructClass(new ConfigExample(), YamlConfiguration.class);
        ConfigLoader.createConfig(file, cachedConfigFields);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
