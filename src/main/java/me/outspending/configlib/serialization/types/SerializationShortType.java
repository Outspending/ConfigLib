package me.outspending.configlib.serialization.types;

import me.outspending.configlib.files.ConfigFile;
import me.outspending.configlib.serialization.SerializationType;
import org.jetbrains.annotations.NotNull;

public class SerializationShortType implements SerializationType<Short> {

    @Override
    public @NotNull Short parse(String value) {
        return Short.parseShort(value);
    }

    @Override
    public @NotNull String serialize(Short value) {
        return value.toString();
    }

    @Override
    public void addToConfig(ConfigFile<?> configFile) {

    }
}
