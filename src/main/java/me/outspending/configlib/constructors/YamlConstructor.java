package me.outspending.configlib.constructors;

import me.outspending.configlib.ConfigCreator;
import me.outspending.configlib.ConstructorType;

// K is the type (YamlConfiguration, FileConfiguration, etc.)
// V is the value (List<CachedConfigField<?>>)
public class YamlConstructor<K, V> extends ConstructorType<K, V> {

    public YamlConstructor(K key, V value) {
        super(key, value);
    }

    @Override
    public K construct() {
        return null;
    }

    @Override
    public void save() {

    }
}
