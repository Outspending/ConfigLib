package me.outspending.configlib;

import me.outspending.configlib.examples.ConfigExample;
import me.outspending.configlib.files.YamlFile;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ConfigLib extends JavaPlugin {

    @Override
    public void onEnable() {
        ConfigInstance instance = ConfigLoader.getInstance();

        // Creating a new YamlFile
        YamlFile file = new YamlFile(getDataFolder(), "config.yml");
        instance.createConfigAsync(file, new ConfigExample()).thenAccept(config -> {
            int integer = config.getSerializedValue("integer-testing", Integer.class);
            Bukkit.getLogger().info("Integer: " + integer);
        });
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
