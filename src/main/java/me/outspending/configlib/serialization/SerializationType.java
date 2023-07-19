package me.outspending.configlib.serialization;

import me.outspending.configlib.CachedConfigField;
import me.outspending.configlib.files.ConfigFile;
import org.jetbrains.annotations.NotNull;

public interface SerializationType<T> {

    /**
     * Parse a string into a value of type T
     *
     * @param value
     * @return
     */
    @NotNull T parse(String value);

    /**
     * Serializes a value of type T into a string
     *
     * @param value
     * @return
     */
    @NotNull String serialize(T value);
}
