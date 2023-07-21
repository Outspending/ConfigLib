package me.outspending.configlib.files;

import me.outspending.configlib.CachedConfigField;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;

@ApiStatus.Internal
public interface ConfigFile<T> {

    /**
     * Adds a field to the ConfigFile instance you could also check {@link YamlFile} for an example
     *
     * @param path
     * @param cachedConfigField
     */
    <T> void addField(@NotNull String path, @NotNull CachedConfigField<?> cachedConfigField, @NotNull Class<T> Clazz);

    /**
     * Saves the ConfigFile instance
     */
    void save();

    /**
     * Reloads the ConfigFile instance
     */
    void reload();

    /**
     * Returns the T instance
     *
     * @return
     */
    T get();

    /**
     * Gets a value from the ConfigFile
     *
     * @param path
     * @return
     */
    @Nullable String getValue(@NotNull String path);

    /**
     * Gets a serialized value from the ConfigFile
     *
     * @param path
     * @param type
     * @return
     * @param <T>
     */
    <T> @Nullable T getSerializedValue(@NotNull String path, @NotNull Class<T> type);

    /**
     * Returns the File associated with ConfigFile
     *
     * @return
     */
    File getFile();

    /**
     * Returns the file name associated with ConfigFile
     *
     * @return
     */
    String getFileName();

    /**
     * Checks if the config exists if not create it
     */
    void checkFile();

}
