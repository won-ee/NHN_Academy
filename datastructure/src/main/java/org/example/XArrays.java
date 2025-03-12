package org.example;

import java.lang.reflect.Array;
import java.util.Arrays;

public class XArrays {

    // 3.Lab-01: 배열 복제 생성
    public static int[] copy(int[] arr){
            if (arr == null){
                throw new NullPointerException("입력한 배열이 null입니다.");
            }

            int [] copyArr = new int[arr.length];

            for (int i =0; i<arr.length; i++){
                copyArr[i] = arr[i];
            }

        return copyArr;
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] copy(T[] arr){
        if (arr == null){
            throw new NullPointerException("입력한 배열이 null입니다.");
        }

        T[] copyArr = (T[]) Array.newInstance(arr.getClass().getComponentType(),arr.length);

        for (int i =0; i<arr.length; i++){
            copyArr[i] = arr[i];
        }

        return copyArr;
    }

    //4. Lab-02: 배열 부분 복제 생성
    public static int[] copy(int[] source, int startIndex, int length){
        if (source == null){
            throw new NullPointerException("입력한 배열이 null입니다.");
        }
        if(startIndex <0 ){
            throw new IndexOutOfBoundsException("유효하지 않은 인덱스 입니다.");
        }
        if(source.length<length){
            throw new IllegalArgumentException("유효하지 않은 인덱스 입니다.");
        }

        int idx = length-startIndex+1;
        int[] copyArr = new int[idx];

        for (int i=0; i<idx; i++){
            copyArr[i] = source[i+startIndex];
        }
        System.out.println(Arrays.toString(copyArr));
        return copyArr;
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] copy(T[] source, int startIndex, int length){
        if (source == null){
            throw new IllegalArgumentException("입력한 배열이 null입니다.");
        }
        if(startIndex <0 ){
            throw new IndexOutOfBoundsException("유효하지 않은 인덱스 입니다.");
        }
        if(source.length<length){
            throw new IllegalArgumentException("유효하지 않은 인덱스 입니다.");
        }

        int idx = length-startIndex+1;
        T[] copyArr = (T[]) Array.newInstance(source.getClass().getComponentType(),idx);

        for (int i=0; i<idx; i++){
            copyArr[i] = source[i+startIndex];
        }
        return copyArr;
    }

    // 5. Lab-03: 배열 부분 복사
    public static void copy(int[] source, int srcIndex, int[] destination, int destIndex, int length){
        if (source == null || destination==null){
            throw new NullPointerException("입력한 배열이 null입니다.");
        }
        if (srcIndex>source.length || destIndex>destination.length){
            throw new IllegalArgumentException("배열이 유효한 범위에 있지 않습니다..");
        }
        if(length <0 || source.length<srcIndex+length){
            throw new IllegalArgumentException("유효하지 않은 인덱스 입니다.");
        }
        if(destIndex+length>destination.length){
            throw new IllegalArgumentException("범위가 초과되었습니다.");
        }

        int[] copyArr = Arrays.copyOfRange(source,srcIndex,srcIndex+length);

        for (int i=0; i<length; i++){
            destination[destIndex+i] = copyArr[i];
        }
    }

    public static <T> void copy(T[] source, int srcIndex, T[] destination, int destIndex, int length){
        if (source == null || destination==null){
            throw new NullPointerException("입력한 배열이 null입니다.");
        }
        if (srcIndex>source.length || destIndex>destination.length){
            throw new IllegalArgumentException("배열이 유효한 범위에 있지 않습니다..");
        }

        if(length <0 || source.length<srcIndex+length){
            throw new IllegalArgumentException("유효하지 않은 인덱스 입니다.");
        }
        if(destIndex+length>destination.length){
            throw new IllegalArgumentException("범위가 초과되었습니다.");
        }

        T[] copyArr = Arrays.copyOfRange(source,srcIndex,srcIndex+length);

        for (int i=0; i<length; i++){
            destination[destIndex+i] = copyArr[i];
        }
    }
    // 6. Lab-04: 다차원 배열 복제
    public static int[][] deepCopy(int[][] source){
        if (source==null){
            throw new NullPointerException("배열은 null이 아니여야 합니다.");
        }
        int[][] deepCopyArr = new int[source.length][];

        for (int i=0; i<source.length; i++){
            if (source[i] == null){
                throw new IllegalArgumentException("배열은 null이 아니여야 합니다.");
            }
            deepCopyArr[i] = new int[source[i].length];
        }

        for (int i=0; i<source.length; i++){
            for (int j=0; j<source[i].length; j++){
                deepCopyArr[i][j] = source[i][j];
            }
        }
        return  deepCopyArr;
    }

    @SuppressWarnings("unchecked")
    public static <T> T[][] deepCopy(T[][] source){
        if (source==null){
            throw new NullPointerException("배열은 null이 아니여야 합니다.");
        }
        T[][] deepCopyArr = (T[][]) Array.newInstance(source.getClass().getComponentType(),source.length);

        for (int i=0; i<source.length; i++){
            if (source[i] == null){
                throw new NullPointerException("배열은 null이 아니여야 합니다.");
            }
            deepCopyArr[i] = (T[]) Array.newInstance(source[i].getClass().getComponentType(), source[i].length);

        }

        for (int i=0; i<source.length; i++){
            for (int j=0; j<source[i].length; j++){
                deepCopyArr[i][j] = source[i][j];
            }
        }
        return  deepCopyArr;
    }

    public static Object[] deepCopy(Object[] source) {
        if (source == null) {
            throw new NullPointerException("배열은 null이 아니어야 합니다.");
        }

        Object[] deepCopyArr = new Object[source.length];

        for (int i = 0; i < source.length; i++) {

            if (source[i] == null) {
                throw new IllegalArgumentException("배열의 요소는 null이 아니어야 합니다.");
            }

            if (source[i].getClass().isArray()) {
                Object temp = Array.newInstance(source[i].getClass().getComponentType(),Array.getLength(source[i]));
                for (int j=0; j<Array.getLength(source[i]); j++){
                    Array.set(temp, j, Array.get(source[i], j));
                }
                deepCopyArr[i] = temp;
            } else {
                deepCopyArr[i] = source[i];
            }

        }
        return deepCopyArr;
    }



    // 7. Lab-05: 배열의 동등 비교
    public static boolean equals(int[] array1, int[] array2){
        if(array1 == null || array2 ==null){
            throw  new IllegalArgumentException("배열의 값은 null이 아니여야 합니다.");
        }
        if (array1.length != array2.length){
            return false;
        }
        for (int i=0; i< array1.length; i++){
            if (array1[i] != array2[i]){
                return false;
            }
        }
        return true;
    }

    public static <T> boolean equals(T[] array1, T[] array2){
        if(array1 == null || array2 ==null){
            throw  new IllegalArgumentException("배열의 값은 null이 아니여야 합니다.");
        }
        if (array1.length != array2.length){
            return false;
        }
        for (int i=0; i< array1.length; i++){
            if (array1[i] != array2[i]){
                return false;
            }
        }
        return true;
    }

    // 8. Lab-06: 2차원 배열의 동등 비교
    public static boolean equals(int[][] array1, int[][] array2){
        if(array1 == null || array2 ==null){
            throw  new IllegalArgumentException("배열의 값은 null이 아니여야 합니다.");
        }

        for (int i=0; i<array1.length; i++){
            if (array1[i] == null){
                throw new IllegalArgumentException("배열은 null이 아니여야 합니다.");
            }
        }

        for (int i=0; i<array2.length; i++){
            if (array2[i] == null){
                throw new IllegalArgumentException("배열은 null이 아니여야 합니다.");
            }
        }

        if (array1.length != array2.length){
            return false;
        }

        for (int i=0; i<array1.length; i++){
            if (array1[i].length != array2[i].length){
                return false;
            }
        }

        for (int i=0; i<array1.length; i++){
            for (int j=0; j<array1[i].length; j++){
                if (array1[i][j] != array2[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    public static <T> boolean equals(T[][] array1, T[][] array2){
        if(array1 == null || array2 ==null){
            throw  new IllegalArgumentException("배열의 값은 null이 아니여야 합니다.");
        }

        for (int i=0; i<array1.length; i++){
            if (array1[i] == null){
                throw new IllegalArgumentException("배열은 null이 아니여야 합니다.");
            }
        }

        for (int i=0; i<array2.length; i++){
            if (array2[i] == null){
                throw new IllegalArgumentException("배열은 null이 아니여야 합니다.");
            }
        }

        if (array1.length != array2.length){
            return false;
        }

        for (int i=0; i<array1.length; i++){
            if (array1[i].length != array2[i].length){
                return false;
            }
        }

        for (int i=0; i<array1.length; i++){
            for (int j=0; j<array1[i].length; j++){
                if (array1[i][j] != array2[i][j]){
                    return false;
                }
            }
        }
        return true;
    }







}
