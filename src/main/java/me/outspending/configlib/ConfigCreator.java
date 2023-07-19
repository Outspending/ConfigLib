package me.outspending.configlib;

import me.outspending.configlib.files.ConfigFile;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ConfigCreator {

    public static void writeFile(@NotNull ConfigFile<?> configFile, @NotNull List<CachedConfigField<?>> cachedFields) {
        File file = configFile.getFile();
        if (!file.exists())
            file.mkdirs();

        configFile.checkFile();

        for (CachedConfigField<?> cachedField : cachedFields)
            configFile.addField(cachedField.getValueLine(), cachedField);

        configFile.save();
    }
}
