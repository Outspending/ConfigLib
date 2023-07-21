# ConfigLib
Building Config's faster and more sustainable

ConfigLib was creating to save your time and my time. This library is pretty fast with around `2-5ms` load times.
If you'd like to contact me you could always ask me on discord ❤️

## Adding ConfigLib to Your Project
Adding ConfigLib to your project is very easy just a simple copy and paste :)
### Maven
Repository:
```xml
N/A
```

Dependency:
```xml
N/A
```
### Gradle
Repository:
```gradle
N/A
```

Dependency:
```gradle
N/A
```
Make sure to reload your project ⚠️

## Creating Config Class
Now you're ready to utilize this library!
```java
@Config(linesBetweenValues = 5)
public class ConfigExample implements ConfigClass {

    @ConfigValue("testing-value")
    @Comments({"line 1 of comment", "line 2 of comment"})
    private String testing = "DEFAULT VALUE";

    /*
     * In file: test-testing:
     *            value: ew
     */
    @ConfigValue("test-testing.value")
    private String value = "ew";

    @ConfigValue("integer-testing")
    private int test = 1;
}
```
Very simple isn't it :>
## Getting ConfigFile value
```java
ConfigInstance instance = ConfigLoader.getInstance();
ConfigFile<?> configFile = instance.getConfigFile("config.yml");
int integer = configFile.getSerializedValue("integer-testing", Integer.class);
```
## Registering Config Class
```java
YamlFile file = new YamlFile(getDataFolder(), "config.yml");
instance.createConfig(file, new ConfigExample());
```
## Registering Config Class Asynchronously
Documentation coming soon...
## Reloading Config Classes
Documentation coming soon...
## Saving Config Classes
Documentation coming soon...
## Creating Custom Config Files
```java
public class YamlFile implements ConfigFile<YamlConfiguration> {

    @Override
    public <T> void addField(@NotNull String path, @NotNull CachedConfigField<?> cachedConfigField, @NotNull Class<T> Clazz) {

    }

    @Override
    public void save() {

    }

    @Override
    public void reload() {

    }

    @Override
    public YamlConfiguration get() {
        return null;
    }

    @Override
    public @Nullable String getValue(@NotNull String path) {
        return null;
    }

    @Override
    public <T> @Nullable T getSerializedValue(@NotNull String path, @NotNull Class<T> type) {
        
    }

    @Override
    public File getFile() {
        return null;
    }

    @Override
    public String getFileName() {
        return null;
    }

    @Override
    public void checkFile() {
        
    }
}
```
You can check [YamlFile](https://github.com/Outspending/ConfigLib/blob/main/src/main/java/me/outspending/configlib/files/YamlFile.java) for a better explantation
### Adding Serilization to Custom ConfigFile
In the `addField` method you can add serilization by doing:
```java
Class<?> typeClass = cachedConfigField.getValueType();
SerializationType<T> serializationType = (SerializationType<T>) SerializationHandler.getSerializationType(typeClass);

// Then setting the file path
configuration.set(path, serializationType.serialize((T) cachedConfigField.getValue()));
```
You could also do this with `getSerializedValue` method
## Creating a custom SerilizationType class
```java
public class SerializationExample implements SerializationType<List<String>> {

    @Override
    public @NotNull List<String> parse(String value) {
        // This is where you'll parse the string into whatever type you pick
        return null;
    }

    @Override
    public @NotNull String serialize(List<String> value) {
        // This will be where you convert it into a String
        return null;
    }
}
```
### Registering SerilizationType
```java
ConfigInstance instance = ConfigLoader.getInstance();
instance.registerSerializationType(List.class, new SerializationExample());
```
And that's it! Have fun
---

That's it for now! I'm wanting to make this very customizable but for right now this is all thats customizable about it. 
Make sure to keep updated about recent changes and bug fixes [HERE](https://github.com/Outspending/ConfigLib/releases)
