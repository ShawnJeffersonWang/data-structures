package com.alibaba.datastructure;


/**
 * 基于无序数组实现
 * Type parameters: <E> - 队列中元素类型，必须实现Priority接口
 */
public class PriorityQueue1<E extends Priority> implements Queue<E> {

    // 不能是E，实现Priority接口，数组的类型是Priority
    Priority[] array;
    int size;

    public PriorityQueue1(int capacity) {
        array = new Priority[capacity];
    }

    // O(1)
    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        array[size++] = value;
        return true;
    }

    // 返回优先级最高的索引值
    private int selectMax() {
        int max = 0;
        for (int i = 1; i < size; i++) {
            if (array[i].priority() > array[max].priority()) {
                max = i;
            }
        }
        return max;
    }

    // 找优先级最高的索引值，poll,peek需要用到共同的代码
    // O(n)
    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        int max = selectMax();
        // 不能先删除再返回remove(max)，删除前要记录
        E e = (E) array[max];
        remove(max);
        return e;
    }

    private void remove(int index) {
        if (index < size - 1) {
            // 移动
            System.arraycopy(array, index + 1, array, index, size - 1 - index);
        }
//        size--;
        array[--size] = null;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        int max = selectMax();
        return (E) array[max];
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
