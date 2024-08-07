package org.swsim.attribute;

import java.util.HashMap;
import java.util.Map;

public class Attribute {
    private String value;
    private boolean isCompiled;
    private HashMap<String, Attribute> dependencies;
    private boolean wildCardRoll = false;
    private boolean isTrait = false;

    public Attribute(String value) {
        setValue(value);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        setCompiled(false);
    }

    public boolean isCompiled() {
        return isCompiled;
    }

    public void setCompiled(boolean compiled) {
        isCompiled = compiled;
        if (!compiled)
            dependencies = new HashMap<>();
    }

    public String getRollText() {
        String ret = value;
        for (String depName : dependencies.keySet()) {
            ret = ret.replaceAll(depName, String.format("(%s)", dependencies.get(depName).getRollText()));
        }
        return ret;
    }

    public void addDependency(String depName, Attribute dependency) {
        dependencies.put(depName, dependency);
    }

    public int castToInt() {
        return Integer.parseInt(getRollText());
    }

    public Map<String, Attribute> getDependencies() {
        return dependencies;
    }

    public void setWildCardRoll(boolean toggle) {
        this.wildCardRoll = toggle;
    }

    public boolean isTrait() {
        return isTrait;
    }
}
