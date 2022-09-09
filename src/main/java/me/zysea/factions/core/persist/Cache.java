package me.zysea.factions.core.persist;

import me.zysea.factions.core.util.JSONFile;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;

public abstract class Cache<K extends Comparable, V> extends JSONFile {

    private Map<K, V> cache = new ConcurrentSkipListMap<>();

    public Cache(String fileName){
        super(fileName);
    }

    public V get(K k){
        return cache.get(k);
    }

    public V put(K k, V v){
        return cache.put(k, v);
    }

    public void putAll(Map<K,V> map){
        cache.putAll(map);
    }

    public V del(K k){
        return cache.remove(k);
    }

    public boolean contains(K k){
        return cache.containsKey(k);
    }

    public Collection<V> getValues(){
        return cache.values();
    }

    public Set<K> getKeySet(){
        return cache.keySet();
    }

    public Set<Map.Entry<K, V>> getEntrySet(){
        return cache.entrySet();
    }

    public List<K> sortByValue(V value){
        List<K> result = new ArrayList<>();
        cache.entrySet().stream().filter(e-> value.equals(e.getValue())).forEach(e -> result.add(e.getKey()));
        return result;
    }

    public Map<K, V> getClonedMap(){
        return new HashMap<>(cache);
    }

}
