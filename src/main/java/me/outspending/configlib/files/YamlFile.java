package me.outspending.configlib.files;

import me.outspending.configlib.CachedConfigField;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class YamlFile implements ConfigFile<YamlConfiguration> {

    private final File file;
    private final YamlConfiguration configuration;

    public YamlFile(File file) {
        this.file = file;
        this.configuration = YamlConfiguration.loadConfiguration(file);
    }

    @Override
    public void addField(String path, CachedConfigField<?> cachedConfigField) {
        if (cachedConfigField.hasComments())
            configuration.setComments(path, cachedConfigField.getComments());

        configuration.set(path, cachedConfigField.getValue());
    }

    @Override
    public void save() {
        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void reload() {
        try {
            configuration.load(file);
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
}
