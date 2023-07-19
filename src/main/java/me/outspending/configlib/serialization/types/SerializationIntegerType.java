package me.outspending.configlib.serialization.types;

import me.outspending.configlib.serialization.SerializationType;
import org.jetbrains.annotations.NotNull;

public class SerializationIntegerType implements SerializationType<Integer> {

    @Override
    public @NotNull Integer parse(String value) {
        return Integer.getInteger(value);
    }

    @Override
    public @NotNull String serialize(Object value) {
        return value.toString();
    }
}
