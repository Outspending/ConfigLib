package me.outspending.configlib.examples;

import me.outspending.configlib.annotations.Comments;
import me.outspending.configlib.annotations.Config;
import me.outspending.configlib.ConfigClass;
import me.outspending.configlib.annotations.ConfigValue;

/**
 * This is an example of how to use the ConfigLib
 * <p>
 * This class is annotated with {@code @Config}, which is required for the ConfigLib to work
 * You could also use {@code @Config("config.yml")} to specify the name of the config file
 * <p>
 * This class also implements {@link ConfigClass}, which is required for the ConfigLib to work
 * <p>
 * <li> {@link ConfigValue} The ConfigValue is the path of the value inside the config
 * <p>
 * <li> {@link Comments} This is the comments you want inside the config file. This will always be above the value and spaced 1 line between all the values
 * <p>
 * <li> {@code private String test = "test";} This is the default value of the field. Every value inside the config must have a default value. If not the ConfigLib will throw an error
 */
@Config
public class ConfigExample implements ConfigClass {

    @ConfigValue("testing-value")
    @Comments({"line 1 of comment", "line 2 of comment"})
    private String testing = "ew";

    /*
     * In file: test-testing:
     *            value: ew
     */
    @ConfigValue("test-testing.value")
    private String value = "ew";

    @ConfigValue("integer-testing")
    private int test = 1;
}
