package me.outspending.configlib.serialization;

import me.outspending.configlib.serialization.types.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SerializationHandler {

    protected static final Map<Class<?>, SerializationType<?>> serializationMap = new HashMap<>();

    static {
        serializationMap.put(Integer.class, new SerializationIntegerType());
        serializationMap.put(Double.class, new SerializationDoubleType());
        serializationMap.put(Float.class, new SerializationFloatType());
        serializationMap.put(Long.class, new SerializationLongType());
        serializationMap.put(Short.class, new SerializationShortType());
        serializationMap.put(Byte.class, new SerializationByteType());
        serializationMap.put(Boolean.class, new SerializationBooleanType());
        serializationMap.put(String.class, new SerializationStringType());

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
