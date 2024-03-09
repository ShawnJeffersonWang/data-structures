package com.shawn.algorithm;

import com.shawn.datastructure.TreeNode;

import java.util.LinkedList;

public class LeetCode104_2 {

    /*
    思路：
    1. 使用非递归后序遍历，栈的最大高度即为最大深度
     */
    public int maxDepth(TreeNode root) {
        TreeNode curr = root;
        TreeNode pop = null;

        LinkedList<TreeNode> stack = new LinkedList<>();
        int max = 0;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);

                int size = stack.size();
                if (size > max) {
                    max = size;
                }

                curr = curr.left;
            } else {
                TreeNode peek = stack.peek();
                if (peek.right == null || peek.right == pop) {
                    pop = stack.pop();
                } else {
                    curr = peek.right;
                }
            }
        }
        return max;
    }
}
