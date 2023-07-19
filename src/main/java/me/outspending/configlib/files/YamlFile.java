package me.outspending.configlib.files;

import me.outspending.configlib.CachedConfigField;
import me.outspending.configlib.serialization.SerializationHandler;
import me.outspending.configlib.serialization.SerializationType;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.file.YamlConfigurationOptions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class YamlFile implements ConfigFile<YamlConfiguration> {

    private final File file;
    private final File defaultFile;
    private final YamlConfiguration configuration;
    private final String fileName;

    public YamlFile(File file, String fileName) {
        this.file = file;
        this.defaultFile = new File(file, fileName);
        this.configuration = YamlConfiguration.loadConfiguration(defaultFile);
        this.fileName = fileName;
    }

    @Override
    public void addField(@NotNull String path, @NotNull CachedConfigField<?> cachedConfigField) {
        Class<?> typeClass = cachedConfigField.getValueType();
        SerializationType<?> serializationType = SerializationHandler.getSerializationType(typeClass);
        configuration.set(path, serializationType.serialize(cachedConfigField.getValue()));

        if (cachedConfigField.hasComments()) {
            configuration.setComments(path, cachedConfigField.getComments());
        }
    }

    @Override
    public void save() {
        try {
            configuration.save(defaultFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void reload() {
        try {
            configuration.load(defaultFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public YamlConfiguration get() {
        return configuration;
    }

    @Override
    public @Nullable String getValue(@NotNull String path) {
        return configuration.getString(path);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> @Nullable T getSerializedValue(@NotNull String path, @NotNull Class<T> type) {
        SerializationType<?> serializationType = SerializationHandler.getSerializationType(type);
        if (serializationType == null)
            return (T) getValue(path);

        return (T) serializationType.parse(getValue(path));
    }

    @Override
    public File getFile() {
        return file;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public void checkFile() {
        if (!defaultFile.exists()) {
            try {
                defaultFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
