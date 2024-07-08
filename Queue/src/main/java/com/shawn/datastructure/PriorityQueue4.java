package com.shawn.datastructure;

// 大顶堆实现优先队列
public class PriorityQueue4<E extends Priority> implements Queue<E> {

    Priority[] array;
    int size;

    public PriorityQueue4(int capacity) {
        array = new Priority[capacity];
    }

    /*
    1. 入堆新元素，加入到数组末尾（索引位置child）
    2. 不断比较新加元素与它的父结点（parent）优先级
        - 如果父节点优先级低，则向下移动，并找到下一个parent
        - 知道父节点优先级更高或child==0 为止
     */
    // O(logn) 时间复杂度最糟也是对数级
    @Override
    public boolean offer(E offered) {
        if (isFull()) {
            return false;
        }
        int child = size++;
        int parent = (child - 1) / 2;
        while (child > 0 && offered.priority() > array[parent].priority()) {
            array[child] = array[parent];
            child = parent;
            // 快速加括号.par
            parent = (child - 1) / 2;
        }
        array[child] = offered;
        return true;
    }

    /*
    1. 交换堆顶和尾部元素，让尾部元素出队
    2. （下潜）
        - 从堆顶开始，将父元素与两个孩子较大者交换
        - 直到父元素大于两个孩子，或没有孩子为止
     */
    // O(logn)
    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        swap(0, size - 1);
        size--;
        Priority e = array[size];
        // help GC
        array[size] = null;

        // 下潜
        down(0);
        return (E) e;
    }

    private void down(int parent) {
        int left = 2 * parent + 1;
        int right = left + 1;
        // 假设父元素优先级最高
        int max = parent;
        if (left < size && array[left].priority() > array[max].priority()) {
            max = left;
        }
        if (right < size && array[right].priority() > array[max].priority()) {
            max = right;
        }
        // 有孩子比父亲大
        if (max != parent) {
            swap(max, parent);
            down(max);
        }
    }

    // 交换代码有一定通用性
    private void swap(int i, int j) {
        Priority t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return (E) array[0];
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
