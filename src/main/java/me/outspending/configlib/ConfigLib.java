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
        ConfigLoader.createConfig(new YamlFile(getDataFolder(), "config.yml"), new ConfigExample());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
