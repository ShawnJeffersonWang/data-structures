package com.alibaba.datastructure;

import java.util.Iterator;
import java.util.function.Consumer;

// 链表类和结点类是组合关系，类与类之间是组合关系需要思考外部类还是内部类
// 好处：对外隐藏实现细节
// 使用者只需要知道一个类就好

// Iterable泛型：遍历出的值的类型
public class SinglyLinkedList implements Iterable<Integer> {
    // 头指针
    // 就算没有加自己的结点，不用做空链表的判断，因为里面肯定有一个结点，这个结点就是哨兵
    private Node head = new Node(0, null);

    @Override
    public Iterator<Integer> iterator() {
        // 匿名内部类 -> 带名字内部类
        return new NodeIterator();
    }

    // F6: Convert Anonymous to Inner...
    //  前面没有static，当某一个内部类使用了外部类的一个成员变量时
    // 就不能加static了
    private class NodeIterator implements Iterator<Integer> {
        // Non-static field 'head' cannot be referenced from a static context
        // 遍历的起点变了
        SinglyLinkedList.Node p = head.next;

        @Override
        public boolean hasNext() {
            return p != null;
        }

        @Override
        public Integer next() {
            int v = p.value;
            p = p.next;
            return v;
        }
    }

    /**
     * 结点类
     */
    // 加了static, 类是相对独立的, 和外部类的成员变量没有任何关系
    // 能加static还是加上
    private static class Node {
        int value;
        Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

//    public void addFirst(int value) {
//        // 1.链表为空
////        head = new Node(value, null);
//        // 2.链表非空
//        head = new Node(value, head);
//    }

    public void addFirst(int value) {
        insert(0, value);
    }

    public void loop1(Consumer<Integer> consumer) {
        Node p = head.next;
        while (p != null) {
            //对链表的结点操作最好不要写在循环里面，而是当做参数传递进来
//            System.out.println(p.value);
            consumer.accept(p.value);
            p = p.next;
        }
    }

    public void loop2(Consumer<Integer> consumer) {
        for (Node p = head.next; p != null; p = p.next) {
            consumer.accept(p.value);
        }
    }

//    private Node findLast() {
//        if (head == null) {
//            return null;
//        }
//
//        Node p;
//        // null.next报空指针
//        for (p = head; p.next != null; p = p.next) {
//
//        }
//        return p;
//    }

    private Node findLast() {
        Node p;
        // null.next报空指针
        for (p = head; p.next != null; p = p.next) {

        }
        return p;
    }

//    public void addLast(int value) {
//        // 考虑两种情况，比较麻烦
//        Node last = findLast();
//        if (last == null) {
//            addFirst(value);
//            return;
//        }
//        last.next = new Node(value, null);
//    }

    public void addLast(int value) {
        // 考虑两种情况，比较麻烦
        Node last = findLast();
        last.next = new Node(value, null);
    }

//    public void test() {
//        int i = 0;
//        // 初始化只能定义一个变量
//        for (Node p = head; p != null; p = p.next, i++) {
//            System.out.println(p.value + "索引是: " + i);
//        }
//    }

    // 返回结点对象
//    private Node findNode(int index) {
//        int i = 0;
//        for (Node p = head; p != null; p = p.next, i++) {
//            if (i == index) {
//                return p;
//            }
//        }
//        // index非法
//        return null;
//    }

    private Node findNode(int index) {
        int i = -1;
        for (Node p = head; p != null; p = p.next, i++) {
            if (i == index) {
                return p;
            }
        }
        // index非法
        return null;
    }

    // 对外界来说，并不关心结点对象，而是结点的值
    public int get(int index) {
        Node node = findNode(index);
        if (node == null) {
            // 抛异常
            throw illegalIndex(index);
        }
        return node.value;
    }

    // Ctrl+Alt+M: 抽取方法
    private static IllegalArgumentException illegalIndex(int index) {
        return new IllegalArgumentException(
                String.format("index [%d] 不合法%n", index));
    }

    /**
     * 向索引位置加入
     */
//    public void insert(int index, int value) {
//        if (index == 0) {
//            addFirst(value);
//            return;
//        }
//        // 上一个结点
//        Node prev = findNode(index - 1);
//        // 找不到
//        if (prev == null) {
//            throw illegalIndex(index);
//        }
//        prev.next = new Node(value, prev.next);
//    }
    public void insert(int index, int value) {
        Node prev = findNode(index - 1);
        // 找不到
        if (prev == null) {
            throw illegalIndex(index);
        }
        prev.next = new Node(value, prev.next);
    }

    // 通过head找不到1了，1与链表没有联系了，
    // 1结点占用的内存将来会不会泄露呢? 在Java中不会,如果有一个对象，将来没有任何人引用他，垃圾回收会将他释放
    // Ctrl+End: 移动一页的最后
//    public void removeFirst() {
//        if (head == null) {
//            throw illegalIndex(0);
//        }
//        head = head.next;
//    }

    // 看成是remove的特殊情况
    public void removeFirst() {
        remove(0);
    }

//    public void remove(int index) {
//        if (index == 0) {
//            removeFirst();
//            return;
//        }
//        // 上一个结点
//        Node prev = findNode(index - 1);
//        // prev找到了
//        if (prev == null) {
//            throw illegalIndex(index);
//        }
//        // prev找到了,though
//        // 被删除的结点
//        Node removed = prev.next;
//        if (removed == null) {
//            throw illegalIndex(index);
//        }
//        prev.next = removed.next;
//    }

    public void remove(int index) {
        // 上一个结点
        Node prev = findNode(index - 1);
        // prev找到了
        if (prev == null) {
            throw illegalIndex(index);
        }
        // prev找到了,though
        // 被删除的结点
        Node removed = prev.next;
        if (removed == null) {
            throw illegalIndex(index);
        }
        prev.next = removed.next;
    }
}



