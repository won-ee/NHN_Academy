package org.example;

public class Main {
    public static void main(String[] args) {
        XQueue<Integer> queue = new XArrayQueue<>(3);  // 초기 크기 3

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        System.out.println(queue.dequeue());  // 출력: 10
        queue.enqueue(40);

        System.out.println(queue.peek());  // 출력: 20
        System.out.println(queue.size());  // 출력: 3

        queue.enqueue(50);  // 크기 자동 확장 발생 (크기: 6)
        System.out.println(queue.size());  // 출력: 4

    }
}