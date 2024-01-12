package com.alibaba.datastructure;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
    1. synchronized 关键字，功能少
    2. ReentrantLock 可重入锁，功能多
 */
public class TestThreadUnsafe {

    private final String[] array = new String[10];
    private int tail = 0;
    private int size = 0;
    // 锁对象，可能要跨方法，多个方法需要用的同一个锁对象，向上提取作为整个类的成员变量
    ReentrantLock lock = new ReentrantLock();
    // 条件变量对象 集合 存储多个阻塞的线程
    Condition tailWaits = lock.newCondition();

    public void offer(String e) throws InterruptedException {
        // 打断点Suspend要选择Thread
//        lock.lock();
        // 加锁（可以在阻塞状态随时打断）
        lock.lockInterruptibly();
        // 中间代码可能出现异常导致解锁代码不执行，加锁解锁必须成对出现，
        // 加锁写在try块外面，需要保护的代码写在try块里，解锁代码写在finally里面
        try {
            /*
                从tailWaits中唤醒的线程，会与新来的offer线程争抢锁，谁能抢到是不一定的，如果后者先抢到，就会导致条件又发生变化
                这种情况称之为虚假唤醒，唤醒后因该重新检查条件，看是不是得重新进入等待
             */
            while (isFull()) {
                // 满了该做的事，不能是return一个bool值，offer线程阻塞，不满的时候恢复运行
                // 当前线程加入tailWaits，并且让此线程阻塞 唤醒：tailWaits.signal()
                // 一般await要配合while循环，不能使用if
                tailWaits.await();
            }
            array[tail] = e;
            if (++tail == array.length) {
                tail = 0;
            }
            size++;
        } finally {
            lock.unlock();
        }
    }

    private boolean isFull() {
        return size == array.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    public static void main(String[] args) throws InterruptedException {
        TestThreadUnsafe queue = new TestThreadUnsafe();
        for (int i = 0; i < 10; i++) {
            queue.offer("e" + i);
        }
//        queue.offer("e1");
//        queue.offer("e2");
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "添加元素之前");
                queue.offer("e10");
                System.out.println(Thread.currentThread().getName() + "添加元素成功");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "t1").start();

        new Thread(() -> {
            System.out.println("开始唤醒");
            try {
                queue.lock.lockInterruptibly();
                queue.tailWaits.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                queue.lock.unlock();
            }
        }, "t2").start();
    }
}
