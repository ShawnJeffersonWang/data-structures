package com.shawn.datastructure;

import java.util.Iterator;

public class ArrayStack<E> implements Stack<E>, Iterable<E> {

    private E[] array;
    // 栈顶指针
    private int top;

    // 数组操作右边性能更高，所以把右边作为顶部
    /*      底     顶
        0   1   2   3
        a   b   c   d
                         |
                        top
     */

    @SuppressWarnings("all")
    public ArrayStack(int capacity) {
        array = (E[]) new Object[capacity];
    }

    @Override
    public boolean push(E value) {
        if (isFull()) {
            return false;
        }
        // 后++: 先求索引的运算再+1
        array[top++] = value;
        return true;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            return null;
        }
        // top不是栈顶元素，top-1才是栈顶元素
        return array[--top];
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return array[top - 1];
    }

    @Override
    public boolean isEmpty() {
        return top == 0;
    }

    @Override
    public boolean isFull() {
        return top == array.length;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            int p = top;

            @Override
            public boolean hasNext() {
                return p > 0;
            }

            @Override
            public E next() {
                return array[--p];
            }
        };
    }
}
