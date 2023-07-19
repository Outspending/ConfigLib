package me.outspending.configlib;

import me.outspending.configlib.constructors.YamlConstructor;
import me.outspending.configlib.serialization.SerializationHandler;
import me.outspending.configlib.serialization.SerializationType;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ConfigLoader {

    private static @NotNull Field[] getAnnotatedFields(ConfigClass configClass) {
        List<Field> fields = new ArrayList<>();
        Class<?> clazz = configClass.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(ConfigValue.class)) {
                fields.add(field);
                Bukkit.getLogger().info("Found annotated field: " + field.getName() + ", Value: " + field.getAnnotation(ConfigValue.class).value() + ", Type: " + field.getType().getName());
            }
        }
        return fields.toArray(new Field[0]);
    }

    public static <V> @NotNull YamlConstructor<? super V, ?> constructClass(ConfigClass configClass, Class<V> type) {
        List<CachedConfigField<?>> configFields = new ArrayList<>();
        Class<? extends ConfigClass> clazz = configClass.getClass();

        if (clazz.isAnnotationPresent(Config.class)) {
            Field[] annotatedVariables = getAnnotatedFields(configClass);
            for (Field field : annotatedVariables) {
                field.setAccessible(true);

                String value = field.getAnnotation(ConfigValue.class).value();
                Class<?> fieldType = field.getType();
                configFields.add(new CachedConfigField<>(value, fieldType));

                Bukkit.getLogger().info("Found annotated field: " + field.getName() + ", Value: " + value + ", Type: " + fieldType.getName());
            }
        }
        return new YamlConstructor<>(type, configFields);
    }

    public static <T> T parseValue(Class<T> typeClass, String value) {
        return SerializationHandler.parse(typeClass, value);
    }
}
