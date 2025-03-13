package org.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Consumer;

public class XArrayList<T> implements XList<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;

    T[] array;

    @SuppressWarnings("unchecked")
    public XArrayList(){
        this.array =(T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
    public XArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException();
        }
        this.array = (T[]) new Object[capacity];
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
    private void resize(){
        int arrayCapacity = array.length;

        if(arrayCapacity == 0){
            array = (T[]) new Object[DEFAULT_CAPACITY];;
        }

        if (size == arrayCapacity){
            int newCapacity = arrayCapacity * 2;

            array = Arrays.copyOf(array,newCapacity);
            return;
        }

        if (size < (arrayCapacity /2)){
            int newCapacity = arrayCapacity / 2;

            array = Arrays.copyOf(array,Math.max(newCapacity,DEFAULT_CAPACITY));
        }
    }

    @Override
    public void add(T element) {
        if (element == null){
            throw new NullPointerException();
        }
        if (size == array.length){
            resize();
        }
        array[size] = element;
        size++;
    }

    @Override
    public void add(int index, T element) {
        if (index > size || index <0){
            throw new IndexOutOfBoundsException();
        }
        if (index == size){
            add(element);
        }else {
            if (size==array.length){
                resize();
            }

            for (int i=size; i>index; i--){
                array[i] = array[i-1];
            }
            array[index] = element;
            size ++;
        }
    }

    @Override
    public  T remove(int index) {
        if (index >= size || index<0){
            throw new IndexOutOfBoundsException();
        }

        T removedElement = array[index];

        for(int i=index; i<size-1; i++){
            array[i] = array[i+1];
        }
        array[size-1] = null;
        size--;
        return removedElement;
    }

    @Override
    public boolean remove(T element) {
        if (element == null){
            throw new NullPointerException();
        }

        for (int i=0; i<size; i++){
            if (array[i]==element){
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(T element) {

        return indexOf(element) > -1;
    }

    @Override
    public int indexOf(T element) {

        if (element == null){
        throw new NullPointerException();
        }
        for (int i=0; i<size; i++){
            if (array[i]==element){
                return i;
            }
        }
        return -1;
    }

    @Override
    public T get(int index) {
        if(index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }else {
            return (T) array[index];
        }
    }

    @Override
    public void set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        array[index] = element;
    }

    @Override
    public void sort(Comparator<T> comparator) {
        if (array.length ==0){
            throw new IndexOutOfBoundsException();
        }
        mergesort(array,comparator);
    }

    public static <T> void mergesort(T[] arr , Comparator<T> comparator){
        if (arr.length<2) return;

        int mid = arr.length/2;
        T[] left = Arrays.copyOfRange(arr,0,mid);
        T[] right = Arrays.copyOfRange(arr,mid,arr.length);


        mergesort(left,comparator);
        mergesort(right,comparator);

        merge(arr,left,right,comparator);
    }

    public static <T> void merge(T[] arr, T[] left, T[] right,Comparator<T> comparator){
        int i = 0, j = 0, k = 0;

        while (i<left.length && j<right.length){
            if (left[i] == null || right[j] == null) {
                break;
            }

            if(comparator.compare(left[i],right[j])<=0){
                arr[k++] = left[i++];
            }   else{
                arr[k++] = right[j++];
            }
        }
        while (i<left.length){
            if (left[i] == null) {
                break;
            }
            arr[k++] = left[i++];
        }

        while (j<right.length){
            if (right[i] == null) {
                break;
            }
            arr[k++] = right[j++];
        }
    }

    @Override
    public XList<T> subList(int fromIndex, int toIndex) {
        if (fromIndex>size || toIndex>size ||fromIndex<0 ||toIndex<0){
            throw new IndexOutOfBoundsException();
        }
        XList<T> list = new XArrayList<>(toIndex-fromIndex);
        for (int i=fromIndex; i<toIndex; i++){
            list.add(array[i]);
        }

        return list;
    }

    @Override
    public void addAll(XList<T> otherList) {
            for (int i=0; i<otherList.size(); i++) {
                array[size++] = (otherList.get(i));
                if (size==array.length){
                    resize();
                }
            }
    }

    @Override
    public void forEach(Consumer<T> action) {
        for (T element : array){
            System.out.println(element);
        }
    }

    @Override
    public int size() {

        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        Arrays.fill(array, 0, size, null);
        size = 0;
    }

    @Override
    public XList<T> copy() {
        XArrayList<T> copiedList = new XArrayList<>(size);
        for (int i = 0; i < size; i++) {
            copiedList.add(array[i]);
        }
        return copiedList;
    }

}
