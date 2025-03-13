package org.example;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class XArrayMap<K,V> implements XMap<K,V>{
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    Entry<K,V>[] array;

    @SuppressWarnings("unchecked")
    XArrayMap(){
        this.array = new  Entry[DEFAULT_CAPACITY];
        this.size = 0;
    }

    private static class Entry<K,V>{
        K key;
        V value;

        private Entry(K key, V value){
            this.key=key;
            this.value=value;
        }

    }

    private void resize(){
        if (size == array.length){
            array = Arrays.copyOf(array,array.length*2);
        }
        if (size < array.length/2){
            array = Arrays.copyOf(array,Math.max(array.length/2,DEFAULT_CAPACITY));
        }
    }

    private int searchKey(K key){
        if (size==0){
            return -1;
        }
        for (int i=0; i<size; i++){
            if (array[i].key.equals(key)){
                return i;
            }
        }
        return -1;
    }

    private int searchValue(V value){
        for (int i=0; i<size; i++){
            if (array[i].value.equals(value)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public V put(K key, V value) {
        if (key ==null || value==null){
            throw new NullPointerException();
        }
        Entry<K,V> entry = new Entry<>(key, value);

        int index = searchKey(key);
        if(index <0){
            array[size++] = entry;
        }else {
            array[index].value = value;
        }

        if (size==array.length){
            resize();
        }
        return value;
    }

    @Override
    public V get(K key) {
        if (key == null){
            throw new NullPointerException();
        }
        int index = searchKey(key);
        if (index<0){
            return null;
        }
        return array[index].value;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null){
            throw new NullPointerException();
        }
        return searchKey(key) >= 0;
    }

    @Override
    public boolean containsValue(V value) {
        if (value == null){
            throw new NullPointerException();
        }
        return searchValue(value)>=0;
    }

    @Override
    public V remove(K key) {
        if (key == null){
            throw new NullPointerException();
        }
        int index = searchKey(key);
        if (index<0){
            return null;
        }
        V value = array[index].value;
        for (int i=++index; i<size; i++){
            array[i-1] = array[i];
        }
        array[--size] = null;
        if (size == array.length/2){
            resize();
        }
        return value;
    }

    @Override
    public void clear() {
        Arrays.fill(array, null);
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
        for (int i=0; i<size; i++){
            keySet.add(array[i].key);
        }
        return keySet;
    }

    @Override
    public Set<V> values() {
        Set<V> valueSet = new HashSet<>();
        for (int i=0; i<size; i++){
            valueSet.add(array[i].value);
        }
        return valueSet;    }
}
