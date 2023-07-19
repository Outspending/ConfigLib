package me.outspending.configlib.examples;

import me.outspending.configlib.serialization.SerializationType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * This example shows you how to make your own SerializationType type.
 * <p>
 * This example is not used in the ConfigLib library. Because it's a functional Interface and can easily be done in 1 line
 * <p>
 *     <code>SerializationHandler.parse("list string");</code>
 * </p>
 */
public class SerializationExample implements SerializationType<List<String>> {

    @Override
    public @NotNull List<String> parse(String value) {
        return null;
    }
}
