package me.outspending.configlib;

import me.outspending.configlib.files.ConfigFile;
import me.outspending.configlib.serialization.SerializationHandler;
import me.outspending.configlib.serialization.SerializationType;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ConfigInstance {

    public <T> SerializationType<T> getSerializationType(Class<T> clazz) {
        return SerializationHandler.getSerializationType(clazz);
    }

    public <T> SerializationType<T> registerSerializationType(Class<T> clazz, SerializationType<T> type) {
        return SerializationHandler.registerSerializationType(clazz, type);
    }

    public List<CachedConfigField<?>> getCachedFields(ConfigClass configClass) {
        return ConfigLoader.constructClass(configClass);
    }
    public <T> T parseValue(Class<T> typeClass, String value) {
        return SerializationHandler.parse(typeClass, value);
    }

    public @Nullable ConfigFile<?> getConfigFile(String name) {
        return ConfigLoader.getConfigFiles().get(name);
    }

    public void createConfig(@NotNull ConfigFile<?> configFile, @NotNull List<CachedConfigField<?>> cachedFields) {
        long start = System.currentTimeMillis();
        Bukkit.getLogger().info("[ConfigLib] Creating config file " + configFile.getFileName());
        ConfigCreator.writeFile(configFile, cachedFields);
        ConfigLoader.getConfigFiles().put(configFile.getFileName(), configFile);
        Bukkit.getLogger().info("[ConfigLib] Created config file " + configFile.getFileName() + " in " + (System.currentTimeMillis() - start) + "ms");
    }

    public void createConfig(@NotNull ConfigFile<?> configFile, @NotNull ConfigClass configClass) {
        createConfig(configFile, ConfigLoader.constructClass(configClass));
    }
}
