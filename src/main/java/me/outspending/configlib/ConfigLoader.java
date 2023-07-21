package me.outspending.configlib;

import me.outspending.configlib.annotations.Comments;
import me.outspending.configlib.annotations.Config;
import me.outspending.configlib.annotations.ConfigValue;
import me.outspending.configlib.files.ConfigFile;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;

@ApiStatus.NonExtendable
public final class ConfigLoader {

    /**
     * This is a map of all the registered config files
     */
    public static final ConcurrentMap<String, ConfigFile<?>> configFiles = new ConcurrentHashMap<>();

    /**
     * Checks if a class is a config class
     *
     * @param clazz
     * @return
     */
    public static boolean isConfigClass(@NotNull Class<?> clazz) {
        return clazz.isAnnotationPresent(Config.class);
    }

    /**
     * Gets all annotated fields for {@link ConfigValue} and returns them in an array
     *
     * @param configClass
     * @return
     */
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

    /**
     * Constructs a ConfigClass and returns a list of cachedFields. I'd recommend you create config classes through {@link ConfigInstance} instead of this :)
     *
     * @param configClass
     * @return
     */
    public synchronized static @NotNull List<CachedConfigField<?>> constructClass(@NotNull ConfigClass configClass) {
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

    /**
     * Creates a class. I'd recommend you using {@link ConfigInstance} instead of this :)
     *
     * @param configFile
     * @param cachedFields
     * @return
     */
    public synchronized static ConfigFile<?> createConfig(@NotNull ConfigFile<?> configFile, @NotNull List<CachedConfigField<?>> cachedFields) {
        if (!configFiles.containsKey(configFile.getFileName()))
            configFiles.put(configFile.getFileName(), configFile);

        if (configFile.getDefaultFile().exists()) {
            Bukkit.getLogger().info("[ConfigLib] Config file " + configFile.getFileName() + " already exists, skipping creation");
            configFile.reload();
        } else {
            long start = System.currentTimeMillis();
            Bukkit.getLogger().info("[ConfigLib] Creating config file " + configFile.getFileName());
            ConfigCreator.writeFile(configFile, cachedFields);
            Bukkit.getLogger().info("[ConfigLib] Created config file " + configFile.getFileName() + " in " + (System.currentTimeMillis() - start) + "ms");
        }
        return configFile;
    }

    /**
     * Returns the instance of this library. This is very recommended to use instead of anything else. It has all the methods you need.
     *
     * @return
     */
    public static ConfigInstance getInstance() {
        return new ConfigInstance();
    }

    /**
     * Returns all the registered ConfigFiles
     *
     * @return
     */
    public static ConcurrentMap<String, ConfigFile<?>> getConfigFiles() {
        return configFiles;
    }
}
