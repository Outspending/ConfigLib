package me.outspending.configlib;

import me.outspending.configlib.annotations.Comments;
import me.outspending.configlib.annotations.Config;
import me.outspending.configlib.annotations.ConfigValue;
import me.outspending.configlib.constructors.YamlConstructor;
import me.outspending.configlib.files.ConfigFile;
import me.outspending.configlib.serialization.SerializationHandler;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ConfigLoader {

    private static @NotNull Field[] getAnnotatedFields(ConfigClass configClass) {
        List<Field> fields = new ArrayList<>();
        Class<?> clazz = configClass.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            ConfigValue value = field.getAnnotation(ConfigValue.class);
            if (value != null && !Modifier.isFinal(field.getModifiers())) {
                fields.add(field);
                Bukkit.getLogger().info("Found annotated field: " + field.getName() + ", Value: " + field.getAnnotation(ConfigValue.class).value() + ", Type: " + field.getType().getName());
            }
        }
        return fields.toArray(new Field[0]);
    }

    public static <V> @NotNull ConstructorType<? super V, ?> constructClass(ConfigClass configClass, Class<V> type) {
        List<CachedConfigField<?>> configFields = new ArrayList<>();
        Class<? extends ConfigClass> clazz = configClass.getClass();

        if (clazz.isAnnotationPresent(Config.class)) {
            Bukkit.getLogger().info("Found annotated class: " + clazz.getName());
            Field[] annotatedVariables = getAnnotatedFields(configClass);
            for (Field field : annotatedVariables) {

                ConfigValue value = field.getAnnotation(ConfigValue.class);
                Class<?> fieldType = field.getType();
                CachedConfigField<?> cachedConfigField = new CachedConfigField<>(value.value(), fieldType);

                if (field.isAnnotationPresent(Comments.class)) {
                    Comments annotation = field.getAnnotation(Comments.class);
                    cachedConfigField.setComments(List.of(annotation.value()));
                }

                configFields.add(cachedConfigField);
            }
        }
        return new YamlConstructor<>(type, configFields);
    }

    public static void createConfig(@NotNull ConfigFile<?> configFile, @NotNull List<CachedConfigField<?>> cachedFields) {
        ConfigCreator.writeFile(configFile, cachedFields);
    }

    public static <T> T parseValue(Class<T> typeClass, String value) {
        return SerializationHandler.parse(typeClass, value);
    }

    public static ConfigInstance getInstance() {
        return new ConfigInstance();
    }
}
