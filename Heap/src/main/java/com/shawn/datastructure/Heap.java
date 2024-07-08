package com.shawn.datastructure;

import java.util.Arrays;

// 可以扩容的heap,max用于指定是大顶堆还是小顶堆
public class Heap {

    int[] array;
    int size;
    boolean max;

    public int size() {
        return size;
    }

    public Heap(int capacity, boolean max) {
        this.array = new int[capacity];
        this.max = max;
    }

    public int peek() {
        return array[0];
    }

    public int poll() {
        int top = array[0];
        swap(0, size - 1);
        size--;
        down(0);
        return top;
    }

    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void heapify() {
        // 最后一个非叶子节点 size/2-1
        for (int i = size / 2 - 1; i >= 0; i--) {
            down(i);
        }
    }

    private void down(int parent) {
        int left = parent * 2 + 1;
        int right = left + 1;
        int maxOrMin = parent;
        if (left < size && (max ? array[left] > array[parent] : array[left] < array[parent])) {
            maxOrMin = left;
        }
        if (right < size && (max ? array[right] > array[parent] : array[right] < array[parent])) {
            maxOrMin = right;
        }
        if (maxOrMin != parent) {
            swap(maxOrMin, parent);
            down(maxOrMin);
        }
    }

    /**
     * 堆的尾部添加元素
     *
     * @param offered-新元素
     */
    public void offer(int offered) {
        if (size == array.length) {
            // 扩容
            grow();
        }
        up(offered);
        size++;
    }

    private void grow() {
        // 要加括号，不然结果不对
        int capacity = size + (size >> 1);
        int[] newArray = new int[capacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    private void up(int offered) {
        int child = size;
        // 到堆顶
        while (child > 0) {
            int parent = (child - 1) / 2;
            boolean cmp = max ? offered > array[parent] : offered < array[parent];
            if (cmp) {
                array[child] = array[parent];
            } else {
                // 小于父元素
                break;
            }
            child = parent;
        }
        array[child] = offered;
    }

    public static void main(String[] args) {
        Heap left = new Heap(5, true);
        for (int i = 1; i <= 10; i++) {
            left.offer(i);
            System.out.println(Arrays.toString(left.array));
        }
    }
}
