package org.example;

import java.util.Iterator;

public class XListQueue<T> implements XQueue<T> {
    XList<T> listQueue;

    public XListQueue(XList<T> list){
        this.listQueue = list;
    }

    @Override
    public boolean enqueue(T element) {
        listQueue.add(element);
        return true;
    }

    @Override
    public T dequeue() {
        if (listQueue.isEmpty()){
            throw new IllegalStateException();
        }
        return listQueue.remove(0);
    }

    @Override
    public T peek() {
        if (listQueue.isEmpty()){
            throw new IllegalStateException();
        }

        return listQueue.get(0);
    }

    @Override
    public int size() {
        return listQueue.size();
    }

    @Override
    public boolean isEmpty() {
        return listQueue.isEmpty();
    }

    @Override
    public void clear() {
        listQueue.clear();
    }

    @Override
    public XQueue<T> copy() {
        XList<T> copiedList = listQueue.copy();
        return new XListQueue<>(copiedList);
    }

    @Override
    public Iterator<T> iterator() {
        return new ListQueueIterator();
    }

    private class ListQueueIterator implements Iterator<T>{
        int index = 0;
        @Override
        public boolean hasNext() {
            return index < listQueue.size();
        }

        @Override
        public T next() {
            return listQueue.get(index++);
        }
    }

}
