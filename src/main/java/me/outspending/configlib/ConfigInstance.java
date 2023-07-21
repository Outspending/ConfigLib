package me.outspending.configlib;

import me.outspending.configlib.files.ConfigFile;
import me.outspending.configlib.serialization.SerializationHandler;
import me.outspending.configlib.serialization.SerializationType;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.List;
import java.util.Map;

@ApiStatus.NonExtendable
public final class ConfigInstance {

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
        Map<String, ConfigFile<?>> configFileMap = ConfigLoader.getConfigFiles();
        if (!configFileMap.containsKey(configFile.getFileName()))
            configFileMap.put(configFile.getFileName(), configFile);


        if (hasFile(configFile.getFile(), configFile.getFileName())) {
            Bukkit.getLogger().info("[ConfigLib] Config file " + configFile.getFileName() + " already exists, skipping creation");
            configFile.reload();
            return;
        }

        long start = System.currentTimeMillis();
        Bukkit.getLogger().info("[ConfigLib] Creating config file " + configFile.getFileName());
        ConfigCreator.writeFile(configFile, cachedFields);
        Bukkit.getLogger().info("[ConfigLib] Created config file " + configFile.getFileName() + " in " + (System.currentTimeMillis() - start) + "ms");
    }

    public void createConfig(@NotNull ConfigFile<?> configFile, @NotNull ConfigClass configClass) {
        createConfig(configFile, ConfigLoader.constructClass(configClass));
    }

    public boolean containsFile(File file, String name) {
        return ConfigLoader.getConfigFiles().containsKey(name) && hasFile(file, name);
    }

    public boolean hasFile(File file, String fileName) {
        return new File(file, fileName).exists();
    }
}
