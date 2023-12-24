package com.alibaba.datastructure;

import java.util.Iterator;

public class ArrayQueue3<E> implements Queue<E>, Iterable<E> {

    /**
     * 100: 01100100
     * 8:   00001000
     * 4(余数)    00000100
     * 12(商):  00001100
     * <p>
     * 111: 01101111
     * 8:   00001000
     * 7(余数):   00000111
     * 13(商):  00001101
     * <p>
     * 求模运算：
     * - 如果除数是2的n次方
     * - 那么被除数的后n位即为余数(模)
     * - 求被除数的后n位方法: 与2^n-1按位与
     */

    // array不会再重新赋值了，可以用final
    private final E[] array;
    int head = 0;
    int tail = 0;

    @SuppressWarnings("all")
    public ArrayQueue3(int c) {
        // 1. 抛异常
//        if ((capacity & capacity - 1) != 0) {
//            throw new IllegalArgumentException("capacity必须是2的幂");
//        }
        /**
         * 2^4   =16
         * 2^4.? =30
         * 2^5   =32
         * 求2的几次方等于30 -> 求以2为底30的对数
         * (int)log2(30) + 1
         *
         * log2(x)=log10(x) / log10(2)
         */
        // 2. 改成2^n 13 -> 16 22 -> 32
        c -= 1;
        c |= c >> 1;
        c |= c >> 2;
        c |= c >> 4;
        c |= c >> 8;
        c |= c >> 16;
        c += 1;
        array = (E[]) new Object[c];
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        // 新的实现tail本身不是索引,需要根据他计算出索引
//        array[(int) (Integer.toUnsignedLong(tail) % array.length)] = value;
        // 按位与比求模运算性能更高, 求模运算本质是除法
        // 也不用考虑符号位, 按位与找的是后几位, 可以说一举两得
        array[tail & (array.length - 1)] = value;
        tail++;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
//        E value = array[(int) (Integer.toUnsignedLong(head) % array.length)];
        E value = array[head & (array.length - 1)];
        head++;
        return value;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
//        return array[(int) (Integer.toUnsignedLong(head) % array.length)];
        return array[head & (array.length - 1)];
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        // 这里不用担心正确性
        return tail - head == array.length;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            int p = head;

            @Override
            public boolean hasNext() {
                return p != tail;
            }

            @Override
            public E next() {
//                E value = array[(int) (Integer.toUnsignedLong(p) % array.length)];
                E value = array[p & (array.length - 1)];
                p++;
                return value;
            }
        };
    }
}
