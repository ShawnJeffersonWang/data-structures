package com.shawn.algorithm;

import java.util.concurrent.atomic.AtomicLong;

public class LeetCode98_3 {

    public boolean isValidBST(TreeNode node) {
        return doValid(node, new AtomicLong(Long.MIN_VALUE));
    }

    // 之前prev是long,是局部变量，可以把参数做成一个对象,需要调用方法去进行比较和赋值
    // 不能用Long，包装类型，属于不可变类型，AtomicLong可以修改
    // 解法3: 中序遍历递归实现（局部变量记录prev）
    private boolean doValid(TreeNode node, AtomicLong prev) {
        if (node == null) {
            return true;
        }
        boolean a = doValid(node.left, prev);
        if (!a) {
            return false;
        }
        if (prev.get() >= node.val) {
            return false;
        }
        // prev=node.val
        prev.set(node.val);
        return doValid(node.right, prev);
    }
}
