package me.outspending.configlib.serialization.types;

import me.outspending.configlib.files.ConfigFile;
import me.outspending.configlib.serialization.SerializationType;
import org.jetbrains.annotations.NotNull;

public class SerializationStringType implements SerializationType<String> {

    @Override
    public @NotNull String parse(String value) {
        return value;
    }

    @Override
    public @NotNull String serialize(String value) {
        return value;
    }

    @Override
    public void addToConfig(ConfigFile<?> configFile) {

    }
}
