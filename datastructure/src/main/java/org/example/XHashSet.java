package org.example;

import java.util.Hashtable;
import java.util.Iterator;

public class XHashSet<T> implements XSet<T>{
    private final Hashtable<T, Boolean> table;

    public XHashSet(){
        table = new Hashtable<>();
    }

    @Override
    public boolean add(T element) {
        if (element == null){
            throw new NullPointerException();
        }
        return table.put(element,true) == null;
    }

    @Override
    public boolean remove(T element) {
        if (element == null){
            throw new NullPointerException();
        }
        return table.remove(element) != null;
    }

    @Override
    public boolean contains(T element) {
        if (element == null){
            throw new NullPointerException();
        }
        return table.containsKey(element);
    }

    @Override
    public int size() {
        return table.size();
    }

    @Override
    public void clear() {
        table.clear();
    }

    @Override
    public boolean isEmpty() {
        return table.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return table.keySet().iterator();
    }

    @Override
    public XSet<T> union(XSet<T> other) {
        XSet<T> resultSet = new XHashSet<>();
        table.keySet().forEach(resultSet::add);
        for (T ele:other){
            resultSet.add(ele);
        }
        return resultSet;
    }

    @Override
    public XSet<T> intersection(XSet<T> other) {
        XSet<T> resultSet = new XHashSet<>();
        for (T ele:other){
            if (table.get(ele) !=null){
                resultSet.add(ele);
            }
        }
        return resultSet;
    }

    @Override
    public XSet<T> difference(XSet<T> other) {
        XSet<T>  resultSet = new XHashSet<>();
        table.keySet().forEach(resultSet::add);

        for (T ele:other){
            resultSet.remove(ele);
        }
        return resultSet;
    }

    @Override
    public XSet<T> symmetricDifference(XSet<T> other) {
        XSet<T>  resultSet = new XHashSet<>();
        table.keySet().forEach(ele-> {
                if (!other.contains(ele)){
                    resultSet.add(ele);
                }
        });
        for (T ele:other){
            if (!this.contains(ele)){
                resultSet.add(ele);
            }
        }
        return resultSet;
    }

    @Override
    public boolean isSubsetOf(XSet<T> other) {
        int temp = other.size();

        table.keySet().forEach(other::add);

        return temp == other.size();
    }

    @Override
    public boolean isSupersetOf(XSet<T> other) {
       for (T ele : other){
           if (!table.containsKey(ele)){
               return false;
           }
       }
       return true;
    }

    @Override
    public XSet<T> copy() {
        XSet<T>  resultSet = new XHashSet<>();
        table.keySet().forEach(resultSet::add);
        return resultSet;
    }
}
