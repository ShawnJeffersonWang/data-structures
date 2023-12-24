package com.alibaba.datastructure;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestArrayQueue3 {

    @Test
    public void generic() {
        ArrayQueue3<String> queue = new ArrayQueue3<>(3);
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");
        queue.offer("d");
        assertFalse(queue.offer("e"));
        assertFalse(queue.offer("f"));

        assertIterableEquals(List.of("a", "b", "c", "d"), queue);
    }

    @Test
    public void offerLimit() {
        ArrayQueue3<Integer> queue = new ArrayQueue3<>(3);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        // 会扩容到4
        assertFalse(queue.offer(5));
        assertFalse(queue.offer(6));

        assertIterableEquals(List.of(1, 2, 3, 4), queue);
    }

    @Test
    public void peek() {
        ArrayQueue3<Integer> queue = new ArrayQueue3<>(3);
        queue.peek();
        assertNull(queue.peek());
        queue.offer(1);
        assertEquals(1, queue.peek());
        queue.offer(2);
        assertEquals(1, queue.peek());
    }

    @Test
    public void pool() {
        ArrayQueue3<Integer> queue = new ArrayQueue3<>(5);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);

        assertEquals(1, queue.poll());
        assertEquals(2, queue.poll());
        assertEquals(3, queue.poll());
        assertEquals(4, queue.poll());
        assertEquals(5, queue.poll());
        assertNull(queue.peek());
    }

    @Test
    public void boundary() {
        ArrayQueue3<Integer> queue = new ArrayQueue3<>(10);
        queue.head = 2147483640;
        queue.tail = queue.head;

        for (int i = 0; i < 10; i++) {
//            System.out.println(Integer.toUnsignedLong(queue.tail) + " " + Integer.toUnsignedLong(queue.tail) % 10);
            queue.offer(i);
//            queue.tail++;
        }

        for (Integer value : queue) {
            System.out.println(value);
        }

        // c: unsigned int 0~2^32-1
        // java:
    }

    // 神奇测试
    @Test
    public void amazing() {
        int head = 2147483640;
        int tail = 2147483647;
        tail += 5;
        System.out.println(tail);
        System.out.println(tail - head);
    }

    @Test
    public void findNextPowerOfTwo() {
        int c = 30;
        // 求离c最近，比c大的2^n（方法1）
        // 当c为32时结果是64不对，所以需要-1
//        int n = (int) (Math.log(c - 1) / Math.log(2)) + 1;
//        System.out.println(n);
//        System.out.println(1 << n);

        // **: Dangling Javadoc comment
        // *: Block comment

        /*
         * 1
         * 10    2^1
         * 100   2^2
         * 1000  2^3
         */
        // 求离c最近，比c大的2^n（方法2）
        c -= 1;
        c |= c >> 1;
        c |= c >> 2;
        c |= c >> 4;
        c |= c >> 8;
        c |= c >> 16;
        c += 1;
        System.out.println(c);
    }
}
