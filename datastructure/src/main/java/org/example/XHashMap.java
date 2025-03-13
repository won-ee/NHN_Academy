package org.example;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class XHashMap<K,V> implements XMap<K,V> {
    private static final int DEFAULT_CAPACITY = 1<<4;
    int capacity;
    private static final float LOAD_FACTOR = 0.75f;
    Entry<K,V>[] table;
    int size;

    @SuppressWarnings("unchecked")
    public XHashMap(){
        this.table = new Entry[DEFAULT_CAPACITY];
        this.size = 0;
        this.capacity = DEFAULT_CAPACITY;
    }


    private static class Entry<K,V> {
        K key;
        V value;
        Entry<K,V> next;
        private Entry(K key, V value,Entry<K,V> next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Entry<K,V> findEntry(K key, int index){
        Entry<K,V> current = table[index];
        while (current!=null){
            if (current.key.equals(key)){
                return current;
            }
            current=current.next;
        }
        return null;
    }


    @SuppressWarnings("unchecked")
    private void resize(){
        int newCapacity = capacity << 1;
        Entry<K,V>[] newTable = new Entry[newCapacity];

        for (int i=0; i<capacity; i++){
            Entry<K, V> current = table[i];
            while (current != null){
                Entry<K,V> next = current.next;
                int newIndex = current.key.hashCode() & (newCapacity-1);

                current.next = newTable[newIndex];
                newTable[newIndex] = current;
                current = next;
            }
        }
        table = newTable;
        capacity = newCapacity;
    }

    @Override
    public V put(K key, V value) {
        if (key == null || value == null){
            throw new NullPointerException();
        }

        if (size>= capacity*LOAD_FACTOR){
            resize();
        }

        int index = key.hashCode() & (capacity - 1);
        Entry<K,V> current = findEntry(key,index);
        if (current !=null){
           V oldValue = current.value;
            current.value=value;
           return oldValue;
        }

        Entry<K,V> newEntry = new Entry<>(key,value,table[index]);
        table[index] = newEntry;
        size++;
        return null;
    }

    @Override
    public V get(K key) {
        if (key == null){
            throw new NullPointerException();
        }
        int index = key.hashCode() & (capacity-1);

        Entry<K,V>  getEntry = findEntry(key,index);
        if (getEntry == null){
            return null;
        }
        return getEntry.value;
    }

    @Override
    public boolean containsKey(K key) {
        if (key ==null){
            throw new NullPointerException();
        }
        int index = key.hashCode() & (capacity-1);

        Entry<K,V> containEntry = findEntry(key,index);

        return containEntry!=null;
    }

    @Override
    public boolean containsValue(V value) {
        if (value == null){
            throw new NullPointerException();
        }
        for (Entry<K, V> entry : table) {
            Entry<K, V> current = entry;
            while (current != null) {
                if (current.value.equals(value)) {
                    return true;
                }
                current = current.next;
            }
        }
        return false;
    }

    @Override
    public V remove(K key) {
        if (key==null){
            throw new NullPointerException();
        }
        int index = key.hashCode() & (capacity-1);
        Entry<K,V> current = table[index];
        Entry<K,V> prev =  null;

        while (current!=null){
            if(current.key.equals(key)){
                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return current.value;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }

    @Override
    public void clear() {
        Arrays.fill(table,null);
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        for (Entry<K, V> entry : table) {
            Entry<K, V> current = entry;
            while (current != null) {
                keySet.add(current.key);
                current = current.next;
            }
        }
        return keySet;
    }

    @Override
    public Set<V> values() {
        Set<V> valueSet = new HashSet<>();
        for (Entry<K, V> entry : table) {
            Entry<K, V> current = entry;
            while (current != null) {
                valueSet.add(current.value);
                current = current.next;
            }
        }
        return valueSet;
    }
}
