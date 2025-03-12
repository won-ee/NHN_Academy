package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

/**
 * Unit tests for XQueue implementation.
 */
class XQueueTest {
    private XQueue<Integer> queue;

    @BeforeEach
    void setUp() {
        queue = new XArrayQueue<>();  // 구현체가 필요함
    }

    // 요소 추가 및 조회 테스트
    @Test
    void testEnqueueAndPeek() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        assertEquals(10, queue.peek());  // 첫 번째 요소 확인
        assertEquals(3, queue.size());   // 크기 확인
    }

    // 요소 제거 테스트
    @Test
    void testDequeue() {
        queue.enqueue(5);
        queue.enqueue(15);
        queue.enqueue(25);

        assertEquals(5, queue.dequeue());  // FIFO 확인
        assertEquals(15, queue.peek());    // 제거 후 다음 원소 확인
        assertEquals(2, queue.size());     // 크기 감소 확인
    }

    // 비어있는 큐에서 dequeue() 시 예외 발생
    @Test
    void testDequeueFromEmptyQueue() {
        assertThrows(IllegalStateException.class, () -> queue.dequeue());
    }

    // 비어있는 큐에서 peek() 시 예외 발생
    @Test
    void testPeekFromEmptyQueue() {
        assertThrows(IllegalStateException.class, () -> queue.peek());
    }

    // 큐 크기 및 비어 있는지 확인
    @Test
    void testSizeAndIsEmpty() {
        assertTrue(queue.isEmpty());
        queue.enqueue(1);
        assertFalse(queue.isEmpty());
        assertEquals(1, queue.size());
    }

    // 큐 초기화(clear) 테스트
    @Test
    void testClear() {
        queue.enqueue(100);
        queue.enqueue(200);
        queue.clear();

        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    // 큐 복사(copy) 테스트
    @Test
    void testCopy() {
        queue.enqueue(7);
        queue.enqueue(14);
        queue.enqueue(21);

        XQueue<Integer> copyQueue = queue.copy();

        assertEquals(3, copyQueue.size());
        assertEquals(7, copyQueue.peek());
        assertNotSame(queue, copyQueue);  // 복사본이 원본과 다르지만 값은 같아야 함
    }

    //  반복자(iterator) 테스트
    @Test
    void testIterator() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        Iterator<Integer> iterator = queue.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());
        assertEquals(2, iterator.next());
        assertEquals(3, iterator.next());
        assertFalse(iterator.hasNext());
    }
}
