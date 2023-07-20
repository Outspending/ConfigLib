package me.outspending.configlib.serialization.types;

import me.outspending.configlib.serialization.SerializationType;
import org.jetbrains.annotations.NotNull;

public class SerializationFloatType implements SerializationType<Float> {

    @Override
    public @NotNull Float parse(String value) {
        return Float.parseFloat(value);
    }

    @Override
    public @NotNull String serialize(Float value) {
        return value.toString();
    }
}
