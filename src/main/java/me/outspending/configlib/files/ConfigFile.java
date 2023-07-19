package me.outspending.configlib.files;

import me.outspending.configlib.CachedConfigField;

import java.io.File;

public interface ConfigFile<T> {

    void addField(String path, CachedConfigField<?> cachedConfigField);

    void save();

    void reload();

    T get();

    File getFile();

}
