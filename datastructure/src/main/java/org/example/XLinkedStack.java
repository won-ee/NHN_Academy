package org.example;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class XLinkedStack<T> implements XStack<T>{
    XList<T> list;

    public XLinkedStack(){
        this.list = new XLinkedList<>();
    }

    @Override
    public void push(T element) {
        if (element==null){
            throw new NullPointerException();
        }
        list.add(element);
    }

    @Override
    public T pop() {
        if (list.isEmpty()){
            throw new NoSuchElementException();
        }
        return list.remove(list.size()-1);
    }

    @Override
    public T peek() {
        if (list.isEmpty()){
            throw new NoSuchElementException();
        }
        return list.get(list.size()-1);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Iterator<T> iterator() {
        return new listIterator();
    }
    private class listIterator implements Iterator<T>{
        int index = list.size();

        @Override
        public boolean hasNext() {
            return index>0;
        }

        @Override
        public T next() {
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            return list.get(--index);
        }
    }
}
