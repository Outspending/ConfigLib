package me.outspending.configlib.constructors;

/**
 * Constructors have been replaced with {@link me.outspending.configlib.files.ConfigFile}. The constructors are
 * deprecated and will be removed in a future release.
 *
 * @param <K>
 * @param <V>
 */
// K is the type (YamlConfiguration, FileConfiguration, etc.)
// V is the value (List<CachedConfigField<?>>)
@Deprecated(forRemoval = true)
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
