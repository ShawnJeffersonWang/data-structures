package com.shawn.datastructure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestSinglyLinkedList {


    private SinglyLinkedList getLinkedList() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        return list;
    }

    @Test
    @DisplayName("测试 addFirst")
    public void test1() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);

        list.loop2(value -> {
            System.out.println(value);
        });
        // 底层就是调用了迭代器的next和hasNext
        for (Integer value : list) {
            System.out.println(value);
        }
    }

    @Test
    @DisplayName("测试 addLast")
    public void test2() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);

        Assertions.assertIterableEquals(List.of(1, 2, 3, 4), list);
    }

    @Test
    @DisplayName("测试 get")
    public void test3() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);

//        int i = list.get(2);
//        System.out.println(i);
        assertEquals(3, list.get(2));
        // 验证异常，是不是和期望中的异常一致
        assertThrows(IllegalArgumentException.class, () -> list.get(10));
    }

    @Test
    @DisplayName("测试 insert")
    public void test4() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(1); // 0
        list.addLast(2); // 1
        list.addLast(3); // 2
        list.addLast(4); // 3
        list.insert(0, 5);
        // 4
//        list.insert(2, 5);
//        list.insert(4, 5);
//        list.insert(5, 5);
//        list.insert(0, 5);
//        for (Integer value : list) {
//            System.out.println(value);
//        }
        assertIterableEquals(List.of(5, 1, 2, 3, 4), list);
    }

    @Test
    @DisplayName("测试 removeFirst")
    public void test5() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);

        list.removeFirst();
        assertIterableEquals(List.of(2, 3, 4), list);
    }

    @Test
    @DisplayName("测试 remove")
    public void test6() {
//        SinglyLinkedList list1 = new SinglyLinkedList();
//        list1.addLast(1);
//        list1.addLast(2);
//        list1.addLast(3);
//        list1.addLast(4);
//
//        list1.remove(2);
//        for (Integer value : list1) {
//            System.out.println(value);
//        }

//        SinglyLinkedList list2 = new SinglyLinkedList();
//        list2.addLast(1);
//        list2.addLast(2);
//        list2.addLast(3);
//        list2.addLast(4);
//
//        list2.remove(0);
//        for (Integer value : list2) {
//            System.out.println(value);
//        }

//        SinglyLinkedList list3 = new SinglyLinkedList();
//        list3.addLast(1);
//        list3.addLast(2);
//        list3.addLast(3);
//        list3.addLast(4);
//
//        list3.remove(5);
//        for (Integer value : list3) {
//            System.out.println(value);
//        }

        SinglyLinkedList list3 = new SinglyLinkedList();
        list3.addLast(1);
        list3.addLast(2);
        list3.addLast(3);
        list3.addLast(4);

        list3.remove(2);
        assertIterableEquals(List.of(1, 2, 4), list3);
    }

    @Test
    @DisplayName("测试递归遍历")
    public void test7() {
        SinglyLinkedList list = getLinkedList();
        list.loop3(value -> System.out.println(value)
                , value -> System.out.println(value)
        );
    }
}
