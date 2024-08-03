package com.shawn.datastructure;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class DynamicArray implements Iterable<Integer> {

    private int size = 0;
    private int capacity = 8;
    // 惰性初始化的思想
    private int[] array = {};

    public void addLast(int element) {
        add(size, element);
    }

    public void add(int index, int element) {
        checkAndGrow();

        if (index >= 0 && index < size) {
            System.arraycopy(array, index, array, index + 1, size - index);
        }
        array[index] = element;
        size++;
    }

    public int get(int index) {
        return array[index];
    }

    private void checkAndGrow() {
        if (size == 0) {
            array = new int[capacity];
        } else if (size == capacity) {
            // 扩容
            capacity += capacity >> 1;
            int[] newArray = new int[capacity];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    public int remove(int index) {
        int removed = array[index];
        if (index < size - 1) {
            System.arraycopy(array, index + 1, array, index, size - index - 1);
        }
        size--;
        return removed;
    }

    // 这样写不好，forEach只能干一件事，就是打印每个元素
    // 能不能把要做的事情不要写死在方法内，而是通过方法的参数传递进来
    // Java中要做这件事就是传递函数式接口
    // forEach给函数式接口提供什么：当前遍历的array[i]
    // 函数式接口返回什么：不需要返回
    public void foreach(Consumer<Integer> consumer) {
        for (int i = 0; i < size; i++) {
//            System.out.println(array[i]);
            consumer.accept(array[i]);
        }
    }

    // Iterator是接口，得先写实现类
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            int i = 0;

            // 有没有下一个元素
            @Override
            public boolean hasNext() {
                return i < size;
            }

            // 返回当前元素，并移动到下一个元素
            @Override
            public Integer next() {
                return array[i++];
            }
        };
    }

    public IntStream stream() {
        return IntStream.of(Arrays.copyOfRange(array, 0, size));
    }
}
