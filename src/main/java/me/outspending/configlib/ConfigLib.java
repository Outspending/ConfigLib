package me.outspending.configlib;

import me.outspending.configlib.examples.ConfigExample;
import me.outspending.configlib.files.ConfigFile;
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
        ConfigInstance instance = ConfigLoader.getInstance();

        // Creating a new YamlFile
        YamlFile file = new YamlFile(getDataFolder(), "config.yml");
        instance.createConfig(file, new ConfigExample());

        // Getting a ConfigFile instance and getting a value from it
        ConfigFile<?> configFile = instance.getConfigFile("config.yml");
        assert configFile == null;
        int integer = configFile.getSerializedValue("integer-testing", Integer.class);
        Bukkit.broadcastMessage(String.valueOf(integer));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
