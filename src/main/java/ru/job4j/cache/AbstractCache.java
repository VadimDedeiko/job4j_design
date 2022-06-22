package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        cache.put(key, new SoftReference<>(value));
    }

    public V get(K key) {
        SoftReference<V> softReference = cache.getOrDefault(key, new SoftReference<V>(null));
        V value = softReference.get();
        if (value == null) {
            V text = load(key);
            cache.put(key, new SoftReference<V>(text));
        }
        return value;
    }

    protected abstract V load(K key);

}