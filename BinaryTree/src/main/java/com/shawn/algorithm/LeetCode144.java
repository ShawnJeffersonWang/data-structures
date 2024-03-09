package com.shawn.algorithm;

import com.shawn.datastructure.TreeNode;
import com.shawn.datastructure.TreeTraversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LeetCode144 {

    public List<Integer> preorderTraversal(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();

        TreeNode curr = root;
        TreeNode pop = null;

        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                stack.push(curr);
                result.add(curr.val);
                curr = curr.left;
            } else {
                TreeNode peek = stack.peek();
                if (peek.right == null) {
                    pop = stack.pop();
                } else if (peek.right == pop) {
                    pop = stack.pop();
                } else {
                    curr = peek.right;
                }
            }
        }
        return result;
    }
}
