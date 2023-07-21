package me.outspending.configlib;

import me.outspending.configlib.files.ConfigFile;
import me.outspending.configlib.serialization.SerializationHandler;
import me.outspending.configlib.serialization.SerializationType;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@ApiStatus.NonExtendable
public final class ConfigInstance {

    /**
     * Gets the serializationType of a class. This can return null if the type isn't registered
     *
     * @param clazz
     * @return
     * @param <T>
     */
    public <T> SerializationType<T> getSerializationType(Class<T> clazz) {
        return SerializationHandler.getSerializationType(clazz);
    }

    /**
     * This registers a new serializationType for a class.
     *
     * @param clazz
     * @param type
     * @return
     * @param <T>
     */
    public <T> SerializationType<T> registerSerializationType(Class<T> clazz, SerializationType<T> type) {
        return SerializationHandler.registerSerializationType(clazz, type);
    }

    /**
     * This gets the cachedFields of a ConfigClass
     *
     * @param configClass
     * @return
     */
    public List<CachedConfigField<?>> getCachedFields(ConfigClass configClass) {
        return ConfigLoader.constructClass(configClass);
    }

    /**
     * Parses a value from a string from typeClass
     *
     * @param typeClass
     * @param value
     * @return
     * @param <T>
     */
    public <T> T parseValue(Class<T> typeClass, String value) {
        return SerializationHandler.parse(typeClass, value);
    }

    /**
     * Returns a loaded ConfigClass. This can return null if it doesn't contain it
     *
     * @param name
     * @return
     */
    public @Nullable ConfigFile<?> getConfigFile(String name) {
        return ConfigLoader.getConfigFiles().get(name);
    }

    /**
     * Creates a configFile from a ConfigFile and a list of cachedFields {@link CachedConfigField}
     *
     * @param configFile
     * @param cachedFields
     */
    public synchronized ConfigFile<?> createConfig(@NotNull ConfigFile<?> configFile, @NotNull List<CachedConfigField<?>> cachedFields) {
        return ConfigLoader.createConfig(configFile, cachedFields);
    }

    /**
     * Create a config without a list of cachedFields. It will automatically get the cachedFields from the ConfigClass
     *
     * @param configFile
     * @param configClass
     */
    public ConfigFile<?> createConfig(@NotNull ConfigFile<?> configFile, @NotNull ConfigClass configClass) {
        return createConfig(configFile, ConfigLoader.constructClass(configClass));
    }

    /**
     * Checks if the file is created and if the config file contains the file
     *
     * @param file
     * @param name
     * @return
     */
    public boolean containsFile(File file, String name) {
        return ConfigLoader.getConfigFiles().containsKey(name) && hasFile(file, name);
    }

    /**
     * This only checks if the file exists within the directory you specify
     *
     * @param file
     * @param fileName
     * @return
     */
    public boolean hasFile(File file, String fileName) {
        return new File(file, fileName).exists();
    }

    public void reloadAllConfigFiles() {
        for (ConfigFile<?> configFile : ConfigLoader.getConfigFiles().values()) {
            configFile.reload();
        }
    }

    /**
     * Creates a config class asynchronously. Remember this is currently experimental and may not work as expected
     *
     * @param configFile
     * @param cachedFields
     * @return
     */
    @ApiStatus.Experimental
    public CompletableFuture<ConfigFile<?>> createConfigAsync(@NotNull ConfigFile<?> configFile, @NotNull List<CachedConfigField<?>> cachedFields) {
        return CompletableFuture.supplyAsync(() -> createConfig(configFile, cachedFields));
    }

    /**
     * Creates a config class asynchronously. Remember this is currently experimental and may not work as expected
     *
     * @param configFile
     * @param configClass
     * @return
     */
    @ApiStatus.Experimental
    public CompletableFuture<ConfigFile<?>> createConfigAsync(@NotNull ConfigFile<?> configFile, @NotNull ConfigClass configClass) {
        return createConfigAsync(configFile, ConfigLoader.constructClass(configClass));
    }

    /**
     * Reloads all the registered config files. Remember this is currently experimental and may not work as expected
     *
     * @return
     */
    @ApiStatus.Experimental
    public CompletableFuture<Void> reloadAllConfigFilesAsync() {
        return CompletableFuture.supplyAsync(() -> {
            reloadAllConfigFiles();
            return null;
        });
    }

    /**
     * Reloads a config file. Remember this is currently experimental and may not work as expected
     *
     * @param configFile
     * @return
     */
    @ApiStatus.Experimental
    public CompletableFuture<Void> reloadConfigFileAsync(@NotNull ConfigFile<?> configFile) {
        return CompletableFuture.supplyAsync(() -> {
            configFile.reload();
            return null;
        });
    }
}
