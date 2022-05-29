package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
                expand();
        boolean rsl = false;
        int index = indexFor(hash(key.hashCode()));
            if (table[index] == null) {
                table[index] = new MapEntry<>(key, value);
                rsl = true;
                count++;
                modCount++;
            }
        return rsl;
    }


    private int hash(int hashCode) {
        return (capacity - 1) & (hashCode ^ (hashCode >>> 16));
    }

    private int indexFor(int hash) {
        return (capacity - 1) & hash;
    }

    private void expand() {
        float expand = table.length * LOAD_FACTOR;
        if (count >= expand) {
            capacity *= 2;
            MapEntry<K, V>[] tableExpand = new MapEntry[capacity];
            for (MapEntry<K, V> entry : table) {
                int index = indexFor(hash(entry.key.hashCode()));
                tableExpand[index] = entry;
            }
            table = tableExpand;
        }
    }

    @Override
    public V get(K key) {
        int index = indexFor(hash(key.hashCode()));
        return table[index] != null ? table[index].value : null;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int index = indexFor(hash(key.hashCode()));
        if (table[index] != null) {
            table[index] = null;
            count--;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int expectedModCount = modCount;
            private int cursorPoint;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (cursorPoint < table.length && table[cursorPoint] == null) {
                    cursorPoint++;
                }
                return cursorPoint < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[cursorPoint++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            MapEntry<?, ?> mapEntry = (MapEntry<?, ?>) o;
            return Objects.equals(key, mapEntry.key) && Objects.equals(value, mapEntry.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }
}