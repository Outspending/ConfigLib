package me.outspending.configlib;

import me.outspending.configlib.annotations.Comments;
import me.outspending.configlib.annotations.Config;
import me.outspending.configlib.annotations.ConfigValue;
import me.outspending.configlib.files.ConfigFile;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

@ApiStatus.NonExtendable
public final class ConfigLoader {

    public static final Map<String, ConfigFile<?>> configFiles = new HashMap<>();

    public static boolean isConfigClass(@NotNull Class<?> clazz) {
        return clazz.isAnnotationPresent(Config.class);
    }

    public static @NotNull Field[] getAnnotatedFields(@NotNull ConfigClass configClass) {
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

    public static @NotNull List<CachedConfigField<?>> constructClass(@NotNull ConfigClass configClass) {
        List<CachedConfigField<?>> configFields = new ArrayList<>();
        Class<? extends ConfigClass> clazz = configClass.getClass();

        if (!isConfigClass(clazz)) return configFields;

        Field[] annotatedVariables = getAnnotatedFields(configClass);
        for (Field field : annotatedVariables) {
            field.setAccessible(true);

            try {
                Object obj = field.get(configClass);
                Objects.requireNonNull(obj, "Field " + field.getName() + " is null");

                ConfigValue value = field.getAnnotation(ConfigValue.class);
                CachedConfigField<?> cachedConfigField = new CachedConfigField<>(value.value(), obj, clazz.getName(), obj.getClass());

                if (field.isAnnotationPresent(Comments.class)) {
                    Comments annotation = field.getAnnotation(Comments.class);
                    cachedConfigField.setComments(List.of(annotation.value()));
                }

                configFields.add(cachedConfigField);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return configFields;
    }

    public static ConfigInstance getInstance() {
        return new ConfigInstance();
    }

    public static Map<String, ConfigFile<?>> getConfigFiles() {
        return configFiles;
    }
}
