package com.shawn.algorithm;

public class LeetCode98_2 {

    long prev = Long.MIN_VALUE;

    // 解法2：中序遍历递归实现（全局变量记录prev）
    public boolean isValidBST(TreeNode node) {

        if (node == null) {
            return true;
        }
        boolean a = isValidBST(node.left);
        // 剪枝
        if (!a) {
            return false;
        }
        // 值
        if (prev >= node.val) {
            return false;
        }
        prev = node.val;
        return isValidBST(node.right);
    }
}
