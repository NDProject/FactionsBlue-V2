package me.zysea.factions.core.flags;

import java.util.*;

public abstract class InteractiveFlag<T> {

    private final String name;
    private List<T> entries = new ArrayList<>();

    public InteractiveFlag(String name){
        this.name = name; // FINAL!
    }

    public String getName(){
        return name;
    }

    public boolean matchesName(String name){
        String query = name.contains(" ") && name.contains("_") ? name.replace(" ", "_") : name;
        return query.equalsIgnoreCase(name);
    }

    public List<T> getIds(){
        return entries;
    }

    public T getFirst(){
        return entries.get(0);
    }

    public boolean isEmpty(){
        return entries.size() == 0;
    }

    public boolean add(T t){
        return entries.add(t);
    }

    public boolean del(T t){
        return entries.remove(t);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InteractiveFlag<?> that = (InteractiveFlag<?>) o;
        return matchesName(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
