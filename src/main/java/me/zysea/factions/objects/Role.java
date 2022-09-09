package me.zysea.factions.objects;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Role {

    private String name;
    private Map<String, Boolean> rules = new HashMap<>();

    public Role(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRule(String key, boolean value){
        rules.put(key.toUpperCase(), value);
    }

    public void delRule(String key){
        rules.remove(key.toUpperCase());
    }

    public boolean hasPermission(String rule){
        if(!rules.containsKey(rule.toUpperCase()))
            return false;

        Boolean result = rules.get(rule.toUpperCase());

        return result != null && result;
    }
}
