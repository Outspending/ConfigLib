package me.outspending.configlib.serialization;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SerializationHandler {

    protected static final Map<Class<?>, SerializationType<?>> serializationMap = new HashMap<>();

    static {
        serializationMap.put(Integer.class, Integer::parseInt);
        serializationMap.put(Double.class, Double::parseDouble);
        serializationMap.put(Float.class, Float::parseFloat);
        serializationMap.put(Long.class, Long::parseLong);
        serializationMap.put(Short.class, Short::parseShort);
        serializationMap.put(Byte.class, Byte::parseByte);
        serializationMap.put(Boolean.class, Boolean::parseBoolean);
        serializationMap.put(String.class, String::valueOf);

        // TODO: Add List serialization and Set serialization
    }

    @SuppressWarnings("unchecked")
    public static <T> SerializationType<T> getSerializationType(Class<T> clazz) {
        return (SerializationType<T>) serializationMap.get(clazz);
    }

    public static <T> T parse(Class<T> clazz, String value) {
        return (T) getSerializationType(clazz).parse(value);
    }
}
