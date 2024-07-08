package com.shawn.datastructure;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1. 异常中断->finally块内解锁
 * 2. 虚假唤醒->while循环里await
 * 3. 指令交错->原子变量
 * 4. signal没有配合锁->try块加锁,finally块解锁
 * 5. 死锁问题->不要写成嵌套方式
 * <p>
 * jps看java进程ID，用jstack后面跟进程ID看死锁
 */
@SuppressWarnings("all")
public class BlockingQueue2<E> implements BlockingQueue<E> {

    private final E[] array;
    private int head;
    private int tail;

    // 不同锁保护的方法会产生指令交错
    // 所以要用原子变量保护size的线程安全
    private AtomicInteger size = new AtomicInteger();

    private ReentrantLock tailLock = new ReentrantLock();
    private Condition tailWaits = tailLock.newCondition();
    private ReentrantLock headLock = new ReentrantLock();
    private Condition headWaits = headLock.newCondition();

    public BlockingQueue2(int capacity) {
        this.array = (E[]) new Object[capacity];
    }

    private boolean isEmpty() {
        return size.get() == 0;
    }

    private boolean isFull() {
        return size.get() == array.length;
    }

    @Override
    public void offer(E e) throws InterruptedException {
        tailLock.lockInterruptibly();
        // 添加前的元素个数
        int c;
        try {
            // 1. 队列满则等待
            while (isFull()) {
                tailWaits.await();
            }
            // 2. 不满则入队
            array[tail] = e;
            if (++tail == array.length) {
                tail = 0;
            }

            // 3. 修改size
            c = size.getAndIncrement();
            if (c + 1 < array.length) {
                tailWaits.signal();
            }
            /*
                1. 读取成员变量size的值
                2. 自增
                3. 结果写会成员变量size
             */
        } finally {
            tailLock.unlock();
        }

        // 4. 唤醒等待非空的poll线程
        // 写成平级的方式，不要嵌套
        /*  级联通知/唤醒(Cascade Wake-up)的思想：
            offer只做一次唤醒，剩余的唤醒交给poll
            poll_1唤醒poll_2，poll_2唤醒poll_3
        */
        // 降低使用headLock锁的次数
        // 0->1    1->2    2->3
        // offer_1 offer_2 offer_3
        if (c == 0) {
            headLock.lock();
            try {
                headWaits.signal();
            } finally {
                headLock.unlock();
            }
        }
    }

    @Override
    public boolean offer(E e, long timeout) throws InterruptedException {
        return false;
    }

    @Override
    public E poll() throws InterruptedException {
        // 返回值变量定义在外层
        E e;
        // 取走前的元素个数
        int c;
        headLock.lockInterruptibly();
        try {
            // 1. 队列空则等待
            while (isEmpty()) {
                // poll_1, poll_2
                headWaits.await();
            }

            // 2. 非空则出队
            e = array[head];
            array[head] = null;
            if (++head == array.length) {
                head = 0;
            }

            // 3. 修改size
            c = size.getAndDecrement();
            // 3->2   2->1   1->0
            // poll_1 poll_2 poll_3
            if (c > 1) {
                headWaits.signal();
            }
            /*
                1. 读取成员变量size的值
                2. 自减
                3. 结果写回成员变量size
             */
        } finally {
            headLock.unlock();
        }

        // 4.队列从满->不满时 由poll唤醒等待不满的offer线程
        if (c == array.length) {
            tailLock.lock();
            try {
                tailWaits.signal();
            } finally {
                tailLock.unlock();
            }
        }

        return e;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new BlockingQueue2<>(3);
        new Thread(() -> {
            try {
                // headWaits底层是链表
                String poll = queue.poll();
                System.out.println("poll_1" + poll);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "poll_1").start();

        new Thread(() -> {
            try {
                String poll = queue.poll();
                System.out.println("poll_2" + poll);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "poll_2").start();

        new Thread(() -> {
            try {
                queue.offer("任务");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "offer").start();
    }
}
