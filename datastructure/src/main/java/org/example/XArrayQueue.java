package org.example;

import java.util.Arrays;
import java.util.Iterator;

public class XArrayQueue<T> implements XQueue<T>{
    private static final int INITIAL_CAPACITY = 10;
    private int front;
    private int rear;
    private int size;
    T[] array;

    @SuppressWarnings("unchecked")
    public XArrayQueue() {
        this.array = (T[]) new Object[INITIAL_CAPACITY];
        this.rear = 0;
        this.front = 0;
        this.size =0;
    }

    @SuppressWarnings("unchecked")
    public XArrayQueue(int capacity) {
        if (capacity<0){
            throw new IllegalArgumentException();
        }
        this.array = (T[]) new Object[capacity];
        this.rear = 0;
        this.front = 0;
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
    private void resize(){
        int newCapacity = array.length * 2;
        T[] newArray = (T[]) new Object[newCapacity];

        for (int i=0; i<size; i++){
            newArray[i] = array[(front+i)%array.length];
        }
        array=newArray;
        front = 0;
        rear = size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean enqueue(Object element) {
        if (element == null){
            throw new NullPointerException();
        }
        if (size==array.length){
            resize();
        }
        array[rear] = (T) element;
        rear = (rear + 1) % array.length;
        size++;
        return true;
    }

    @Override
    public T dequeue() {
        if (rear==0){
            throw new IllegalStateException();
        }
        T element = array[front];
        array[front] = null;
        front = (front + 1) % array.length;
        size --;
        return element;
    }

    @Override
    public T peek() {
        if (size==0){
            throw new IllegalStateException();
        }
        return array[front];
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
    public void clear() {
        Arrays.fill(array,0,rear,null);
        rear = 0;
        front = 0;
        size = 0;
    }

    @Override
    public XQueue<T> copy() {
        XArrayQueue<T> copiedList = new XArrayQueue<>(rear);
        for (int i=0; i<rear; i++){
            copiedList.enqueue(array[i]);
        }
        return copiedList;
    }

    @Override
    public Iterator<T> iterator() {

        return new QueueIterator();
    }
    public class QueueIterator implements Iterator<T> {
        int index = front;
        int count = 0;

        @Override
        public boolean hasNext() {
            return count<size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NullPointerException();
            }
            T element = array[index];
            count++;
            index = (index + 1) % array.length;

            return element;
        }
    }

}
