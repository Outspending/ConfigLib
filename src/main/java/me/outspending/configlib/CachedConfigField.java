package me.outspending.configlib;

import java.util.ArrayList;
import java.util.List;

// K is the default value of the field
public class CachedConfigField<K> {

    private List<String> comments = new ArrayList<>();
    private final String valueLine;
    private final K value;
    private final Class<?> valueType;
    private final String className;

    public CachedConfigField(String valueLine, K value, String className) {
        this.valueLine = valueLine;
        this.value = value;
        this.valueType = value.getClass();
        this.className = className;
    }

    public List<String> getComments() {
        return comments;
    }

    public String getValueLine() {
        return valueLine;
    }

    public K getValue() {
        return value;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public Class<?> getValueType() {
        return valueType;
    }

    public boolean hasComments() {
        return !comments.isEmpty();
    }

    public String getClassName() {
        return className;
    }
}
