package org.example;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class XArrayStack<T> implements XStack<T> {
    private static final int DEFAULT_CAPACITY = 10;
    int size;
    T[] array;

    @SuppressWarnings("unchecked")
    public XArrayStack(){
        this.array = (T[])new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
    private void resize(){
        if (array.length==0){
            this.array = (T[])new Object[DEFAULT_CAPACITY];
        }
        if(size==array.length){
            array=Arrays.copyOf(array,array.length * 2);
        }
        if (size<array.length/2){
            array=Arrays.copyOf(array,Math.max(array.length/2, DEFAULT_CAPACITY));
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void push(Object element) {
        if (element==null){
            throw new NullPointerException();
        }

        if (size==array.length){
            resize();
        }

        array[size] = (T) element;
        size++;
    }

    @Override
    public T pop() {
        if(size==0){
            throw new NoSuchElementException();
        }

        T temp = array[--size];
        array[size] = null;

        if (size<array.length/2){
            resize();
        }
        return temp;
    }

    @Override
    public T peek() {
        if(size==0){
            throw new NoSuchElementException();
        }
        return array[size-1];
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        array = (T[])new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public Iterator<T> iterator() {

        return new StackIterator();
    }

    private class StackIterator implements Iterator<T>{
        int index = size;
        @Override
        public boolean hasNext() {
            return index >0;
        }

        @Override
        public T next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            return array[--index];
        }
    }
}
