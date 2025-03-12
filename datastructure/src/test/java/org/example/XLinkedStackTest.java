package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * XLinkedStack 클래스의 테스트를 수행합니다.
 */
public class XLinkedStackTest {
    private XStack<Integer> stack;

    /**
     * 각 테스트 메서드 실행 전 스택을 초기화합니다.
     */
    @BeforeEach
    void setUp() {
        stack = new XLinkedStack<Integer>(); // 구현체를 여기에 사용
    }

    /**
     * 요소 추가 및 크기 확인 테스트를 수행합니다.
     */
    @Test
    void testPushAndSize() {
        stack.push(10);
        stack.push(20);
        assertEquals(2, stack.size());
    }

    /**
     * pop()을 사용하여 요소 제거 및 반환 테스트를 수행합니다.
     */
    @Test
    void testPop() {
        stack.push(10);
        stack.push(20);

        assertEquals(20, stack.pop()); // 가장 최근에 삽입된 요소 반환
        assertEquals(1, stack.size());

        assertEquals(10, stack.pop()); // 다음 요소 반환
        assertEquals(0, stack.size());
    }

    /**
     * pop()을 빈 스택에서 호출할 경우 예외 발생 테스트를 수행합니다.
     */
    @Test
    void testPopOnEmptyStack() {
        assertThrows(NoSuchElementException.class, () -> stack.pop());
    }

    /**
     * peek()을 사용하여 스택의 맨 위 요소 확인 테스트를 수행합니다.
     */
    @Test
    void testPeek() {
        stack.push(10);
        stack.push(20);

        assertEquals(20, stack.peek()); // 가장 위의 요소 확인 (20)
        assertEquals(2, stack.size()); // 요소는 제거되지 않음
    }

    /**
     * peek()을 빈 스택에서 호출할 경우 예외 발생 테스트를 수행합니다.
     */
    @Test
    void testPeekOnEmptyStack() {
        assertThrows(NoSuchElementException.class, () -> stack.peek());
    }

    /**
     * isEmpty()가 올바르게 동작하는지 확인 테스트를 수행합니다.
     */
    @Test
    void testIsEmpty() {
        assertTrue(stack.isEmpty());

        stack.push(10);
        assertFalse(stack.isEmpty());

        stack.pop();
        assertTrue(stack.isEmpty());
    }

    /**
     * clear()를 사용하여 모든 요소 제거 테스트를 수행합니다.
     */
    @Test
    void testClear() {
        stack.push(10);
        stack.push(20);

        stack.clear();
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    /**
     * Null 값 입력 검증 테스트를 수행합니다.
     */
    @Test
    void testNullValidation() {
        assertThrows(NullPointerException.class, () -> stack.push(null));
    }
}
