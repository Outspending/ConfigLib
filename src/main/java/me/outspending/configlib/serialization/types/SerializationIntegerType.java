package me.outspending.configlib.serialization.types;

import me.outspending.configlib.files.ConfigFile;
import me.outspending.configlib.serialization.SerializationType;
import org.jetbrains.annotations.NotNull;

public class SerializationIntegerType implements SerializationType<Integer> {

    @Override
    public @NotNull Integer parse(String value) {
        return Integer.getInteger(value);
    }

    @Override
    public @NotNull String serialize(Integer value) {
        return value.toString();
    }

    @Override
    public void addToConfig(ConfigFile<?> configFile) {

    }
}
