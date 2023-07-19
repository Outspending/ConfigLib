package me.outspending.configlib.examples;

import me.outspending.configlib.files.ConfigFile;
import me.outspending.configlib.serialization.SerializationType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * This example shows you how to make your own SerializationType type.
 * <p>
 *     <code>SerializationHandler.parse("list string");</code>
 * </p>
 */
public class SerializationExample implements SerializationType<List<String>> {

    @Override
    public @NotNull List<String> parse(String value) {
        return null;
    }

    @Override
    public @NotNull String serialize(List<String> value) {
        return null;
    }

    @Override
    public void addToConfig(ConfigFile<?> configFile) {

    }
}
