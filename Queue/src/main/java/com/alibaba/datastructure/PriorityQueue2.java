package com.alibaba.datastructure;

/**
 * 基于有序数组实现
 * Type parameters: <E> - 队列中元素类型，必须实现Priority接口
 * 优先级越大排在数组的尾部
 * 出队变得简单了，入队复杂了
 */
public class PriorityQueue2<E extends Priority> implements Queue<E> {

    Priority[] array;
    int size;

    public PriorityQueue2(int capacity) {
        array = new Priority[capacity];
    }

    // O(n)
    @Override
    public boolean offer(E e) {
        if (isFull()) {
            return false;
        }
        // 插入的代码有一定的通用性
        insert(e);
        size++;
        return true;
    }

    // 经典插入排序算法
    private void insert(E e) {
        int i = size - 1;
        while (i >= 0 && array[i].priority() > e.priority()) {
            array[i + 1] = array[i];
            i--;
        }
        array[i + 1] = e;
    }

    // O(1)
    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E e = (E) array[size - 1];
        // 数组存储的是引用类型，光size-1，数组还引用着刚才的对象
        // help GC
        array[--size] = null;
        return e;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return (E) array[size - 1];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == array.length;
    }
}
