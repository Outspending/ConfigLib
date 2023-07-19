package me.outspending.configlib.files;

import me.outspending.configlib.CachedConfigField;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public interface ConfigFile<T> {

    /**
     * Adds a field to the ConfigFile instance you could also check {@link YamlFile} for an example
     *
     * @param path
     * @param cachedConfigField
     */
    void addField(@NotNull String path, @NotNull CachedConfigField<?> cachedConfigField);

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
