package com.alibaba.datastructure;

import java.util.Iterator;

/**
 * 环形数组实现
 * 好处:
 * 1. 对比普通数组，起点和终点更为自由，不用考虑数据移动
 * 任意一个索引都可以作为头和尾，而不像普通数组只能0作为头
 * 2. "环"意味着不会存在越界问题
 * 3. 数组性能更佳
 * 数组可以充分利用局部性原理和CPU缓存
 * 4. 环形数组比较适合实现有界队列，RingBuffer等
 * <p>
 * 下标计算:
 * (cur+step)%length
 * <p>
 * 判空:
 * head=tail
 * 判满:
 * (tail+1)%5==head
 */
public class ArrayQueue1<E> implements Queue<E>, Iterable<E> {


    private final E[] array;
    private int head = 0;
    private int tail = 0;

    // 抑制警告产生
    @SuppressWarnings("all")
    public ArrayQueue1(int capacity) {
        array = (E[]) new Object[capacity + 1];
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        array[tail] = value;
        tail = (tail + 1) % array.length;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E value = array[this.head];
        head = (head + 1) % array.length;
        return value;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return array[head];
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        return (tail + 1) % array.length == head;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int p = head;

            @Override
            public boolean hasNext() {
                return p != tail;
            }

            @Override
            public E next() {
                E value = array[p];
                p = (p + 1) % array.length;
                return value;
            }
        };
    }
}
