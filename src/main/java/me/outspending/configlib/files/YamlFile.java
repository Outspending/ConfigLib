package me.outspending.configlib.files;

import me.outspending.configlib.CachedConfigField;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

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
        Bukkit.getLogger().info(this.file.getAbsoluteFile().toString());
        this.configuration = YamlConfiguration.loadConfiguration(defaultFile);
        this.fileName = fileName;
    }

    @Override
    public void addField(String path, CachedConfigField<?> cachedConfigField) {
        configuration.set(path, cachedConfigField.getValue());

        if (cachedConfigField.hasComments())
            configuration.setComments(path, cachedConfigField.getComments());
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
