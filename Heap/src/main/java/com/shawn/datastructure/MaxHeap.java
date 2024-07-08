package com.shawn.datastructure;

import java.util.Arrays;

// Floyd建堆算法，也是之前龟兔赛跑判环作者
// 2^h-h-1
// 2^h=n,h=log2n h转化为数据规模n,时间复杂度O(n),

/**
 * 大顶堆
 */
public class MaxHeap {

    int[] array;
    int size;

    public MaxHeap(int capacity) {
        this.array = new int[capacity];
    }

    public MaxHeap(int[] array) {
        this.array = array;
        this.size = array.length;
        heapify();
    }

    // 建堆
    // 如何找到最后这个非叶子节点 size / 2 - 1
    private void heapify() {
        for (int i = size / 2 - 1; i >= 0; i--) {
            down(i);
        }
    }

    public void remove(int index) {

    }

    /**
     * 删除堆顶元素
     *
     * @Returns: 堆顶元素
     */
    public int poll() {
        int top = array[0];
        swap(0, size - 1);
        size--;
        down(0);
        return top;
    }

    /**
     * 删除指定索引处元素
     *
     * @Params: index-索引
     * @Returns: 被删除元素
     */
    public int poll(int index) {
        int deleted = array[index];
        swap(index, size - 1);
        size--;
        down(index);
        return deleted;
    }

    /**
     * 替换堆顶元素
     *
     * @Params: replaced-新元素
     */
    public void replace(int replaced) {
        array[0] = replaced;
        down(0);
    }

    /**
     * 获取堆顶元素
     *
     * @Returns: 堆顶元素
     */
    public int peek() {
        return array[0];
    }

    /**
     * 堆的尾部添加元素
     *
     * @Params: offered-被添加的元素值
     * @Returns: 是否添加成功
     */
    public boolean offer(int offered) {
        if (size == array.length) {
            return false;
        }
        up(offered);
        size++;
        return true;
    }

    // 将offered元素上浮：直至offered小于父元素或到堆顶
    // 计算出offered的父元素，新加的元素索引size, 然后-1, 然后/2
    private void up(int offered) {
        int child = size;
        while (child > 0) {
            int parent = (child - 1) / 2;
            if (offered > array[parent]) {
                array[child] = array[parent];
            } else {
                break;
            }
            child = parent;
        }
        array[child] = offered;
    }

    // 将parent索引处的元素下潜：与两个孩子较大者交换，直到没孩子或孩子没它大
    private void down(int parent) {
        int left = parent * 2 + 1;
        int right = left + 1;
        int max = parent;
        // left>=size：没有孩子，arrray[left]和array[right]都<array[max]：没有孩子比他大
        if (left < size && array[left] > array[max]) {
            max = left;
        }
        if (right < size && array[right] > array[max]) {
            max = right;
        }
        // 找到了更大的孩子
        if (max != parent) {
            swap(max, parent);
            // 起点是左右两个孩子中较大的，对他进行递归
            down(max);
        }
    }

    // 交换两个索引处的元素
    private void swap(int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    public static void main(String[] args) {
//        int[] array = {1, 2, 3, 4, 5, 6, 7};
//        MaxHeap heap = new MaxHeap(array);
//        System.out.println(Arrays.toString(heap.array));
//
//        while (heap.size > 1) {
//            heap.swap(0, heap.size - 1);
//            heap.size--;
//            heap.down(0);
//        }
//
//        System.out.println(Arrays.toString(heap.array));
        // 堆排序
        /*
         heapify建立大顶堆
         将堆顶与堆底交换（最大元素被交换到堆底），缩小并下潜调整堆
         重复第二步直至堆里只剩一个元素
         */
        int[] array = {2, 3, 1, 7, 6, 4, 5};
        MaxHeap heap = new MaxHeap(array);
        System.out.println(Arrays.toString(heap.array));
        while (heap.size > 1) {
            heap.swap(0, heap.size - 1);
            heap.size--;
            heap.down(0);
        }

        System.out.println(Arrays.toString(heap.array));
    }
}
