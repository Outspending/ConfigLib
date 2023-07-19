package me.outspending.configlib.serialization.types;

import me.outspending.configlib.serialization.SerializationType;
import org.jetbrains.annotations.NotNull;

public class SerializationLongType implements SerializationType<Long> {

    @Override
    public @NotNull Long parse(String value) {
        return Long.parseLong(value);
    }

    @Override
    public @NotNull String serialize(Object value) {
        return value.toString();
    }
}
