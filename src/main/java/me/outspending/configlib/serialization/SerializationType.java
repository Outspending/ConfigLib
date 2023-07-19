package me.outspending.configlib.serialization;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface SerializationType<T> {

    @NotNull T parse(String value);
}
