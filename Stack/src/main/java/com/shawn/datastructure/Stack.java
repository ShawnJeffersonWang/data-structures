package com.shawn.datastructure;

// Backspace是针对字符的删除，而Del是针对文件的删除
// 将IDEA中的代码粘贴到MarkDown中默认带代码格式
public interface Stack<E> {

    /**
     * @param value 待压入值
     * @return 压入成功返回true，否则返回false
     */
    boolean push(E value);

    E pop();

    E peek();

    boolean isEmpty();

    boolean isFull();
}
