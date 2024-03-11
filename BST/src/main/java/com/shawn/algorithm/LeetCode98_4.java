package com.shawn.algorithm;

public class LeetCode98_4 {

    /*
               -无穷  4  +无穷
                  /      \
          -无穷  2    4    6 +无穷
                         /   \
                    4   3  6  7  +无穷
     */
    // 解法4：上下限递归实现
    public boolean isValidBST(TreeNode node) {
        return doValid(node, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean doValid(TreeNode node, long min, long max) {
        if (node == null) {
            return true;
        }
        if (node.val <= min || node.val >= max) {
            return false;
        }
        return doValid(node.left, min, node.val) &&
                doValid(node.right, node.val, max);
    }
}
