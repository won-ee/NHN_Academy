package org.example;

import java.util.Comparator;
import java.util.Iterator;

public class XPriorityQueue<T> implements XQueue<T> {
    XList<T> priorityQueue;
    private Comparator<T> comparator;

    public XPriorityQueue(Comparator<T> comparator){
        this.priorityQueue = new XLinkedList<>();
        this.comparator = comparator;
    }

    @Override
    public boolean enqueue(T element) {
        if (element == null){
            throw new NullPointerException();
        }
        if (priorityQueue.isEmpty()){
           priorityQueue.add(element);
           return true;
        }
        int index=0;
        for (int i=0; i<priorityQueue.size(); i++){
            if(comparator.compare(priorityQueue.get(i),element)>0) {
                break;
            }
            index++;
        }
        priorityQueue.add(index,element);
        return true;
    }

    @Override
    public T dequeue() {
        if (priorityQueue.isEmpty()){
            throw new NullPointerException();
        }
        return priorityQueue.remove(0);
    }

    @Override
    public T peek() {
        if (priorityQueue.isEmpty()){
            throw new NullPointerException();
        }
        return priorityQueue.get(0);
    }

    @Override
    public int size() {
        return priorityQueue.size();
    }

    @Override
    public boolean isEmpty() {
        return priorityQueue.isEmpty();
    }

    @Override
    public void clear() {
        priorityQueue.clear();
    }

    @Override
    public XQueue<T> copy() {
        XList<T> copiedList = priorityQueue.copy();
        return new XListQueue<>(copiedList);
    }
    @Override
    public Iterator<T> iterator() {
        return new PriorityQueueIterator();
    }

    private class PriorityQueueIterator implements Iterator<T>{
        int index = 0;
        @Override
        public boolean hasNext() {
            return index < priorityQueue.size();
        }

        @Override
        public T next() {
            return priorityQueue.get(index++);
        }
    }

}



