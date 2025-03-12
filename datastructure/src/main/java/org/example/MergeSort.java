//package org.example;
//
//import java.util.Arrays;
//import java.util.Comparator;
//
//public class MergeSort {
//    public static <T> void mergesort(T[] arr , Comparator<T> comparator){
//        if (arr.length<2) return;
//
//        int mid = arr.length/2;
//        T[] left = Arrays.copyOfRange(arr,0,mid);
//        T[] right = Arrays.copyOfRange(arr,mid,arr.length);
//
//
//        mergesort(left,Comparator<T> comparator);
//        mergesort(right,Comparator<T> comparator);
//
//        merge(arr,left,right);
//    }
//
//    public static <T> void merge(T[] arr, T[] left, T[] right){
//        int i = 0, j = 0, k = 0;
//
//        while (i<left.length && j<right.length){
//            if(left[i]<=right[j]){
//                arr[k] = left[i];
//                i++;
//            }   else{
//                arr[k] = right[j];
//                j++;
//            }
//            k++;
//        }
//        while (i<left.length){
//            arr[k] = left[i];
//            k++;
//            i++;
//        }
//
//        while (j<right.length){
//            arr[k] = right[j];
//            k++;
//            j++;
//        }
//    }
//}
