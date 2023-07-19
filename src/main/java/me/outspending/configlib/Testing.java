package me.outspending.configlib;

@Config
public class Testing implements ConfigClass {

    @ConfigValue("test-testing")
    @Comments({"line 1 of comment", "line 2 of comment"})
    private final String test = "test";

    @ConfigValue("testing-value")
    private final String testing = "ew";
}
