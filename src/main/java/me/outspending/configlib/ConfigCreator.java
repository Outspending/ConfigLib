package me.outspending.configlib;

import me.outspending.configlib.files.ConfigFile;
import me.outspending.configlib.serialization.SerializationHandler;
import me.outspending.configlib.serialization.SerializationType;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;

public class ConfigCreator {

    public static void writeFile(@NotNull ConfigFile<?> configFile, @NotNull List<CachedConfigField<?>> cachedFields) {
        File file = configFile.getFile();
        if (!file.exists()) {
            file.mkdir();
        }

        for (CachedConfigField<?> cachedField : cachedFields) {
            SerializationType<?> serializationType = SerializationHandler.getSerializationType(cachedField.getValueType());
            serializationType.addToConfig(configFile);
        }
    }
}
