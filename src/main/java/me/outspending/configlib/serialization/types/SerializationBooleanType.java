package me.outspending.configlib.serialization.types;

import me.outspending.configlib.CachedConfigField;
import me.outspending.configlib.files.ConfigFile;
import me.outspending.configlib.serialization.SerializationType;
import org.jetbrains.annotations.NotNull;

public class SerializationBooleanType implements SerializationType<Boolean> {

    @Override
    public @NotNull Boolean parse(String value) {
        return Boolean.parseBoolean(value);
    }

    @Override
    public @NotNull String serialize(Boolean value) {
        return value.toString();
    }
}
