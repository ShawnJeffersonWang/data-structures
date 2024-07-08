package com.shawn.datastructure;

/**
 * 目前队列存在的问题
 * 1. 很多场景要求分离生产者，消费者两个角色，他们得由不同的线程来担当，而之前的实现根本没有考虑线程安全问题
 * 2. 队列为空，那么在之前的实现里会返回null，如果就是硬要拿到一个元素呢？只能不断循环尝试
 * 3. 队列为满，那么在之前的视线里会返回false，如果就是硬要塞入一个元素呢？只能不断循环尝试
 * 解决方法
 * 1. 用锁保证线程安全
 * 2. 用条件变量让poll或offer线程进入等待状态，而不是不断循环尝试，让CPU空转
 */

// 阻塞队列
public interface BlockingQueue<E> {

    void offer(E e) throws InterruptedException;

    boolean offer(E e, long timeout) throws InterruptedException;

    E poll() throws InterruptedException;
}
