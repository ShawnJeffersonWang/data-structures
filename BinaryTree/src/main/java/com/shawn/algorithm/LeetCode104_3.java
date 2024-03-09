package com.shawn.algorithm;

import com.shawn.datastructure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class LeetCode104_3 {

    /*
    思路：
    1. 使用层序遍历，层数即为最大深度
     */
//    public int maxDepth(TreeNode root) {
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.offer(root);
//
//        int max = 0;
//
//        // 每层有几个元素
//        int c1 = 1;
//        while (!queue.isEmpty()) {
//            int c2 = 0;
//            for (int i = 0; i < c1; i++) {
//                TreeNode node = queue.poll();
//                System.out.print(node.val + "\t");
//                if (node.left != null) {
//                    queue.offer(node.left);
//                    c2++;
//                }
//                if (node.right != null) {
//                    queue.offer(node.right);
//                    c2++;
//                }
//            }
//            c1 = c2;
//            System.out.println();
//        }
//        return max;
//    }
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
//                System.out.print(node.val + "\t");
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
//            System.out.println();
            depth++;
        }
        return depth;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                new TreeNode(new TreeNode(4),
                        2,
                        new TreeNode(new TreeNode(7), 5, null)
                ),
                1,
                new TreeNode(null, 3, new TreeNode(6)));
        int depth = new LeetCode104_3().maxDepth(root);
        System.out.println("depth: " + depth);
    }
}
