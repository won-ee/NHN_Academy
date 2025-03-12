package org.example;

import java.util.Comparator;
import java.util.function.Consumer;

public class XLinkedList<T> implements XList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public XLinkedList(){
        this.head = null;
        this.tail = null;
        this.size =0;
    }

    private static class Node<E>{
        private E item;
        private Node<E> next;

        Node(E item, Node<E> next){
            this.item = item;
            this.next = next;
        }

    }

    private Node<T> findNode(int index){
        if (index<0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        Node<T> current = head;

        for (int i=0; i<index; i++){
            current = current.next;
        }

        return current;
    }

    private int findNode(T element){
        Node<T> current = head;

        for (int i=0; i<size; i++){
            if (current.item.equals(element)){
                return i;
            }
            current = current.next;
        }

        return -1;
    }

    @Override
    public void add(T element) {
        if (element==null){
            throw new NullPointerException();
        }
        Node<T> newNode = new Node<>(element,null);
        if (head == null){
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    @Override
    public void add(int index, T element) {
        if (element==null){
            throw new NullPointerException();
        }
        if (index < 0 || index>=size){
            throw new IndexOutOfBoundsException();
        }
        if (index==0){
            head = new Node<>(element,head);
            size++;
            return;
        }
        if (index == size){
            add(element);
            return;
        }
        Node<T> prevNode = findNode(index-1);
        Node<T> nextNode = prevNode.next;
        prevNode.next = new Node<>(element,nextNode);
        size++;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index>=size){
            throw new IndexOutOfBoundsException();
        }
        if (index ==0){
            T removedItem = head.item;
            head = head.next;
            size--;
            return removedItem;
        }
        if (index==size-1){
            Node<T> prevNode = findNode(index-1);
            T removedItem = tail.item;
            prevNode.next=null;
            tail = prevNode;
            size--;
            return removedItem;
        }
        Node<T> prevNode = findNode(index-1);
        Node<T> nowNode = prevNode.next;
        prevNode.next = nowNode.next;
        size--;
        return nowNode.item;
    }

    @Override
    public boolean remove(T element) {
        if (element ==null){
            throw new NullPointerException();
        }
        int findIndex = findNode(element);
        if (findIndex>=0){
            remove(findIndex);
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(T element) {
        if (element ==null){
            throw new NullPointerException();
        }
        int findIndex = findNode(element);

        return findIndex >= 0;
    }

    @Override
    public int indexOf(T element) {
        if (element ==null){
            throw new NullPointerException();
        }

        return findNode(element);
    }

    @Override
    public T get(int index) {
        if (index < 0 || index>=size){
            throw new IndexOutOfBoundsException();
        }

        Node<T> current= findNode(index);

        return current.item;
    }

    @Override
    public void set(int index, T element) {
        if (index < 0 || index>=size){
            throw new IndexOutOfBoundsException();
        }
        Node<T> current = findNode(index);
        current.item = element;
    }

    @Override
    public void sort(Comparator<T> comparator) {
        if (size==0){
            throw new IndexOutOfBoundsException();
        }
        head = mergeSort(head,comparator);
        tail = head;
        while (tail!=null && tail.next !=null){
            tail = tail.next;
        }
    }

    private Node<T> mergeSort(Node<T> head, Comparator<T> comparator) {
        if (head==null || head.next ==null){
            return head;
        }
        Node<T> middle = fineMiddle(head);
        Node<T> nextMiddle = middle.next;

        middle.next= null;


        Node<T> left = mergeSort(head,comparator);
        Node<T> right = mergeSort(nextMiddle,comparator);

        return merge(left,right,comparator);
    }

    private Node<T>fineMiddle(Node<T> head){
        if (head == null || head.next == null) {
            return head;
        }
        Node<T> middle = head;
        Node<T> end = head;
        while (end.next!=null && end.next.next !=null ){
            middle = middle.next;
            end = end.next.next;
        }
        return middle;
    }

    private  Node<T> merge(Node<T> left, Node<T> right, Comparator<T> comparator){
        if (left==null) return right;
        if (right==null) return left;

        Node<T> result;
        if (comparator.compare(left.item, right.item) <= 0) {
            result = left;
            result.next = merge(left.next, right, comparator);
        } else {
            result = right;
            result.next = merge(left, right.next, comparator);
        }
        return result;
    }

    @Override
    public XList<T> subList(int fromIndex, int toIndex) {
        if (fromIndex<0 || toIndex>size || fromIndex>size){
            throw new IndexOutOfBoundsException();
        }

        XList<T> lst = new XLinkedList<>();
        for (int i=fromIndex; i<toIndex; i++){
            Node<T> current = findNode(i);
            lst.add(current.item);
        }
        return lst;
    }

    @Override
    public void addAll(XList<T> otherList) {
        for (int i=0; i<otherList.size(); i++){
            T item = otherList.get(i);
            add(item);
        }
    }

    @Override
    public void forEach(Consumer<T> action) {
        Node<T> current = head;
        for (int i=0; i<=size-1; i++){
            System.out.println(current.item);
            current= current.next;
        }
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
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public XList<T> copy() {
        XList<T> copiedList = new XLinkedList<>();
        for (int i=0; i<size; i++){
            Node<T> current = findNode(i);
            copiedList.add(current.item);
        }
        return copiedList;
    }
}
