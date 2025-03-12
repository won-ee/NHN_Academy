package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class XArraysTest {
    // copy(int[] source) 테스트
    @Test
    void testCopyIntArray() {
        int[] source = { 1, 2, 3, 4, 5 };
        int[] destination = XArrays.copy(source);
        assertArrayEquals(source, destination);
    }

    @Test
    void testCopyIntArray_NullSource() {
        assertThrows(NullPointerException.class, () -> XArrays.copy((int[]) null));
    }

    // copy(T[] source) 테스트
    @Test
    void testCopyGenericArray() {
        String[] source = { "a", "b", "c" };
        String[] destination = XArrays.copy(source);
        assertArrayEquals(source, destination);
    }

    @Test
    void testCopyGenericArray_NullSource() {
        assertThrows(NullPointerException.class, () -> XArrays.copy((String[]) null));
    }

    // copy(int[] source, int startIndex, int length) 테스트
    @Test
    void testCopyIntArrayWithStartAndLength() {
        int[] source = { 1, 2, 3, 4, 5 };
        int[] destination = XArrays.copy(source, 1, 3);
        assertArrayEquals(new int[] { 2, 3, 4 }, destination);
    }

    @Test
    void testCopyIntArrayWithStartAndLength_NullSource() {
        assertThrows(NullPointerException.class, () -> XArrays.copy((int[]) null, 0, 0));
    }

    @Test
    void testCopyIntArrayWithStartAndLength_InvalidParams() {
        int[] source = { 1, 2, 3, 4, 5 };
        assertThrows(IndexOutOfBoundsException.class, () -> XArrays.copy(source, -1, 3));
        assertThrows(IllegalArgumentException.class, () -> XArrays.copy(source, 2, 10));
    }

    // copy(T[] source, int startIndex, int length) 테스트
    @Test
    void testCopyGenericArrayWithStartAndLength() {
        String[] source = { "a", "b", "c", "d", "e" };
        String[] destination = XArrays.copy(source, 1, 3);
        assertArrayEquals(new String[] { "b", "c", "d" }, destination);
    }

    @Test
    void testCopyGenericArrayWithStartAndLength_NullSource() {
        assertThrows(IllegalArgumentException.class, () -> XArrays.copy((String[]) null, 0, 0));
    }

    @Test
    void testCopyGenericArrayWithStartAndLength_InvalidParams() {
        String[] source = { "a", "b", "c", "d", "e" };
        assertThrows(IndexOutOfBoundsException.class, () -> XArrays.copy(source, -1, 3));
        assertThrows(IllegalArgumentException.class, () -> XArrays.copy(source, 2, 10));
    }

    // copy(int[] source, int srcIndex, int[] destination, int destIndex, int
    // length) 테스트
    @Test
    void testCopyIntArrayToAnotherArray() {
        int[] source = { 1, 2, 3, 4, 5 };
        int[] destination = new int[5];
        XArrays.copy(source, 1, destination, 2, 3);
        assertArrayEquals(new int[] { 0, 0, 2, 3, 4 }, destination);
    }

    @Test
    void testCopyIntArrayToSelf() {
        int[] source = { 1, 2, 3, 4, 5 };
        XArrays.copy(source, 1, source, 2, 3);
        assertArrayEquals(new int[] { 1, 2, 2, 3, 4 }, source);

        XArrays.copy(source, 2, source, 0, 3);
        assertArrayEquals(new int[] { 2, 3, 4, 3, 4 }, source);
    }

    @Test
    void testCopyIntArrayToAnotherArray_NullSourceOrDestination() {
        int[] source = { 1, 2, 3, 4, 5 };
        int[] destination = new int[5];
        assertThrows(NullPointerException.class, () -> XArrays.copy((int[]) null, 0, destination, 0, 0));
        assertThrows(NullPointerException.class, () -> XArrays.copy(source, 0, (int[]) null, 0, 0));
    }

    @Test
    void testCopyIntArrayToAnotherArray_InvalidParams() {
        int[] source = { 1, 2, 3, 4, 5 };
        int[] destination = new int[5];
        assertThrows(IndexOutOfBoundsException.class, () -> XArrays.copy(source, -1, destination, 0, 3));
        assertThrows(IndexOutOfBoundsException.class, () -> XArrays.copy(source, 0, destination, -1, 3));
        assertThrows(IllegalArgumentException.class, () -> XArrays.copy(source, 2, destination, 1, 10));
    }

    // copy(T[] source, int srcIndex, T[] destination, int destIndex, int length)
    // 테스트
    @Test
    void testCopyGenericArrayToAnotherArray() {
        String[] source = { "a", "b", "c", "d", "e" };
        String[] destination = new String[5];
        XArrays.copy(source, 1, destination, 2, 3);
        assertArrayEquals(new String[] { null, null, "b", "c", "d" }, destination);
    }

    @Test
    void testCopyGenericArrayToSelf() {
        String[] source = { "a", "b", "c", "d", "e" };
        XArrays.copy(source, 1, source, 2, 3);
        assertArrayEquals(new String[] { "a", "b", "b", "c", "d" }, source);

        XArrays.copy(source, 2, source, 0, 3);
        assertArrayEquals(new String[] { "b", "c", "d", "c", "d" }, source);
    }

    @Test
    void testCopyGenericArrayToAnotherArray_NullSourceOrDestination() {
        String[] source = { "a", "b", "c", "d", "e" };
        String[] destination = new String[5];
        assertThrows(NullPointerException.class, () -> XArrays.copy((String[]) null, 0, destination, 0, 0));
        assertThrows(NullPointerException.class, () -> XArrays.copy(source, 0, (String[]) null, 0, 0));
    }

    @Test
    void testCopyGenericArrayToAnotherArray_InvalidParams() {
        String[] source = { "a", "b", "c", "d", "e" };
        String[] destination = new String[5];
        assertThrows(IndexOutOfBoundsException.class, () -> XArrays.copy(source, -1, destination, 0, 3));
        assertThrows(IndexOutOfBoundsException.class, () -> XArrays.copy(source, 0, destination, -1, 3));
        assertThrows(IllegalArgumentException.class, () -> XArrays.copy(source, 2, destination, 1, 10));
    }

    @Test
    @DisplayName("copy(int[]) - null 입력 시 NullPointerException 발생")
    void copyIntArray_NullInput() {
        assertThrows(NullPointerException.class, () -> XArrays.copy((int[]) null));
    }

    @Test
    @DisplayName("copy(int[]) - 배열 복사 성공")
    void copyIntArray_Success() {
        int[] source = { 1, 2, 3, 4, 5 };
        int[] destination = XArrays.copy(source);

        assertArrayEquals(source, destination);
        assertNotSame(source, destination, "깊은 복사 여부 확인");
    }

    @Test
    @DisplayName("copy(Integer[]) - null 입력 시 NullPointerException 발생")
    void copyIntegerArray_NullInput() {
        assertThrows(NullPointerException.class, () -> XArrays.copy((Integer[]) null));
    }

    @Test
    @DisplayName("copy(Integer[]) - 배열 복사 성공")
    void copyIntegerArray_Success() {
        Integer[] source = { 1, 2, 3, 4, 5 };
        Integer[] destination = XArrays.copy(source);

        assertArrayEquals(source, destination);
        assertNotSame(source, destination, "깊은 복사 여부 확인");
    }

    @Test
    @DisplayName("deepCopy(int[][]) - null 입력 시 NullPointerException 발생")
    void deepCopyInt2DArray_NullInput() {
        assertThrows(NullPointerException.class, () -> XArrays.deepCopy((int[][]) null));
    }

    @Test
    @DisplayName("deepCopy(int[][]) - 2차원 배열 깊은 복사 성공")
    void deepCopyInt2DArray_Success() {
        int[][] source = { { 1, 2 }, { 3, 4, 5 }, { 6 } };
        int[][] destination = XArrays.deepCopy(source);

        assertArrayEquals(source, destination);
        assertNotSame(source, destination, "깊은 복사 여부 확인");
        for (int i = 0; i < source.length; i++) {
            assertNotSame(source[i], destination[i], "내부 배열 깊은 복사 여부 확인");
            assertArrayEquals(source[i], destination[i]);
        }
    }

    @Test
    @DisplayName("deepCopy(Integer[][]) - null 입력 시 NullPointerException 발생")
    void deepCopyInteger2DArray_NullInput() {
        assertThrows(NullPointerException.class, () -> XArrays.deepCopy((Integer[][]) null));
    }

    @Test
    @DisplayName("deepCopy(Integer[][]) - 2차원 배열 깊은 복사 성공")
    void deepCopyInteger2DArray_Success() {
        Integer[][] source = { { 1, 2 }, { 3, 4, 5 }, { 6 } };
        Integer[][] destination = XArrays.deepCopy(source);

        assertArrayEquals(source, destination);
        assertNotSame(source, destination, "깊은 복사 여부 확인");
        for (int i = 0; i < source.length; i++) {
            assertNotSame(source[i], destination[i], "내부 배열 깊은 복사 여부 확인");
            assertArrayEquals(source[i], destination[i]);
        }
    }

    @Test
    @DisplayName("deepCopy(Object[]) - null 입력 시 NullPointerException 발생")
    void deepCopyObjectArray_NullInput() {
        assertThrows(NullPointerException.class, () -> XArrays.deepCopy((Object[]) null));
    }

    @Test
    @DisplayName("deepCopy(Object[]) - Object 배열 깊은 복사 성공 (기본 타입 배열 포함)")
    void deepCopyObjectArray_Success_WithPrimitiveArray() {
        Object[] source = { 1, "hello", new int[] { 7, 8, 9 }, new String[] { "a", "b" } };
        Object[] destination = XArrays.deepCopy(source);

        assertArrayEquals(source, destination);
        assertNotSame(source, destination, "깊은 복사 여부 확인");

        // 내부 배열 깊은 복사 확인 (int[])
        assertArrayEquals((int[]) source[2], (int[]) destination[2]);
        assertNotSame(source[2], destination[2], "내부 int[] 배열 깊은 복사 여부 확인");

        // 내부 배열 깊은 복사 확인 (String[])
        assertArrayEquals((String[]) source[3], (String[]) destination[3]);
        assertNotSame(source[3], destination[3], "내부 String[] 배열 깊은 복사 여부 확인");
    }

    @Test
    @DisplayName("deepCopy(Object[]) - Object 배열 깊은 복사 성공 (Object 배열 포함)")
    void deepCopyObjectArray_Success_WithObjectArray() {
        Object[] innerArray = { "c", "d" };
        Object[] source = { 1, "hello", innerArray };
        Object[] destination = XArrays.deepCopy(source);

        assertArrayEquals(source, destination);
        assertNotSame(source, destination, "깊은 복사 여부 확인");

        // 내부 배열 깊은 복사 확인 (Object[])
        assertArrayEquals((Object[]) source[2], (Object[]) destination[2]);
        assertNotSame(source[2], destination[2], "내부 Object[] 배열 깊은 복사 여부 확인");
        if (destination[2] instanceof Object[]) {
            assertNotSame(innerArray, (Object[]) destination[2], "내부 Object 배열 깊은 복사 확인");
        }
    }


}