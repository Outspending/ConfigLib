package me.outspending.configlib.serialization.types;

import me.outspending.configlib.serialization.SerializationType;
import org.jetbrains.annotations.NotNull;

public class SerializationDoubleType implements SerializationType<Double> {

    @Override
    public @NotNull Double parse(String value) {
        return Double.parseDouble(value);
    }

    @Override
    public @NotNull String serialize(Double value) {
        return value.toString();
    }
}
