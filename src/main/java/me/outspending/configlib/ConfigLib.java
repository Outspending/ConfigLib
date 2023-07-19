package me.outspending.configlib;

import me.outspending.configlib.examples.ConfigExample;
import me.outspending.configlib.files.YamlFile;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.util.List;

public final class ConfigLib extends JavaPlugin {

    @Override
    public void onEnable() {
        YamlFile file = new YamlFile(getDataFolder(), "config.yml");
        ConfigLoader.getInstance().createConfig(file, new ConfigExample());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
