package me.outspending.configlib.serialization.types;

import me.outspending.configlib.CachedConfigField;
import me.outspending.configlib.files.ConfigFile;
import me.outspending.configlib.serialization.SerializationType;
import org.jetbrains.annotations.NotNull;

public class SerializationByteType implements SerializationType<Byte> {

    @Override
    public @NotNull Byte parse(String value) {
        return Byte.parseByte(value);
    }

    @Override
    public @NotNull String serialize(Byte value) {
        return value.toString();
    }
}
