package me.outspending.configlib.constructors;

import me.outspending.configlib.ConstructorType;

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
