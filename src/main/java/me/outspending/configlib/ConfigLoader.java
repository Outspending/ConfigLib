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
            }
        }
        return fields.toArray(new Field[0]);
    }

    public static <V> @NotNull List<CachedConfigField<?>> constructClass(ConfigClass configClass, Class<V> type) {
        List<CachedConfigField<?>> configFields = new ArrayList<>();
        Class<? extends ConfigClass> clazz = configClass.getClass();

        if (clazz.isAnnotationPresent(Config.class)) {
            Field[] annotatedVariables = getAnnotatedFields(configClass);
            for (Field field : annotatedVariables) {
                field.setAccessible(true);

                try {
                    Object obj = field.get(configClass);
                    ConfigValue value = field.getAnnotation(ConfigValue.class);
                    CachedConfigField<?> cachedConfigField = new CachedConfigField<>(value.value(), obj, clazz.getName());

                    if (field.isAnnotationPresent(Comments.class)) {
                        Comments annotation = field.getAnnotation(Comments.class);
                        cachedConfigField.setComments(List.of(annotation.value()));
                    }

                    configFields.add(cachedConfigField);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return configFields;
    }

    public static void createConfig(@NotNull ConfigFile<?> configFile, @NotNull List<CachedConfigField<?>> cachedFields) {
        ConfigCreator.writeFile(configFile, cachedFields);
    }

    public static void createConfig(@NotNull ConfigFile<?> configFile, @NotNull ConfigClass configClass) {
        createConfig(configFile, constructClass(configClass, configClass.getClass()));
    }

    public static <T> T parseValue(Class<T> typeClass, String value) {
        return SerializationHandler.parse(typeClass, value);
    }

    public static ConfigInstance getInstance() {
        return new ConfigInstance();
    }
}
