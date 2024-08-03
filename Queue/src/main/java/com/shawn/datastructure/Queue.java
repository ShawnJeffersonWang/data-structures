package com.shawn.datastructure;

public interface Queue<E> {

    /**
     * 向队列尾插入值
     *
     * @Params: value-待插入值
     * @Returns: 插入成功返回true, 插入失败返回false
     */
    boolean offer(E value);

    /**
     * 从队列头获取值, 并移除
     *
     * @Returns: 如果队列非空返回头值, 否则返回null
     */
    E poll();

    /**
     * 从队列头获取值, 不排除
     *
     * @Returns: 如果队列非空返回头值, 否则返回null
     */
    E peek();

    boolean isEmpty();

    /**
     * 检查队列是否已满
     *
     * @return 满返回true, 否则返回false
     */
    boolean isFull();
}
