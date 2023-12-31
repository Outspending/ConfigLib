package me.outspending.configlib.constructors;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Constructors have been replaced with {@link me.outspending.configlib.files.ConfigFile}. The constructors are
 * deprecated and will be removed in a future release.
 *
 * @param <K>
 * @param <V>
 */

// K is the type (YamlConfiguration, FileConfiguration, etc.)
// V is the value of CachedConfigField
@Deprecated
public abstract class ConstructorType<K, V> {

    private final List<V> configFields = new ArrayList<>();
    private final K key;
    private final V value;

    public ConstructorType(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public abstract K construct();

    public abstract void save();

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public List<V> getConfigFields() {
        return configFields;
    }
}
