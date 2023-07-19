package me.outspending.configlib.serialization;

import me.outspending.configlib.CachedConfigField;
import me.outspending.configlib.files.ConfigFile;
import org.jetbrains.annotations.NotNull;

public interface SerializationType<T> {

    @NotNull T parse(String value);

    @NotNull String serialize(T value);
}
