package me.outspending.configlib;

import me.outspending.configlib.examples.ConfigExample;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class ConfigLib extends JavaPlugin {

    @Override
    public void onEnable() {
        ConfigLoader.constructClass(new ConfigExample(), YamlConfiguration.class);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
