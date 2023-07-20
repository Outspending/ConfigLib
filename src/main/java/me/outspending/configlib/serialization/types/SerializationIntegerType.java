package me.outspending.configlib.serialization.types;

import me.outspending.configlib.serialization.SerializationType;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public class SerializationIntegerType implements SerializationType<Integer> {

    @Override
    public @NotNull Integer parse(String value) {
        return Integer.parseInt(value);
    }

    @Override
    public @NotNull String serialize(Integer value) {
        return value.toString();
    }
}
