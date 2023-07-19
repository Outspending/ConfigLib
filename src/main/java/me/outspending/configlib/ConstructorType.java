package me.outspending.configlib;

import java.util.ArrayList;
import java.util.List;

// K is the type (YamlConfiguration, FileConfiguration, etc.)
// V is the value of CachedConfigField
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
}
