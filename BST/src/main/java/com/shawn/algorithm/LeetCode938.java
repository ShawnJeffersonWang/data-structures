package com.shawn.algorithm;

/**
 * 求范围和
 */
public class LeetCode938 {

    // 解法2. 上下限递归
    public int rangeSumBST(TreeNode node, int low, int high) {
        if (node == null) {
            return 0;
        }
        if (node.val < low) {
            return rangeSumBST(node.right, low, high);
        }
        if (node.val > high) {
            return rangeSumBST(node.left, low, high);
        }
        // 在范围内
        return node.val + rangeSumBST(node.left, low, high) + rangeSumBST(node.right, low, high);
    }

    // 解法1. 中序非递归实现
//    public int rangeSumBST(TreeNode node, int low, int high) {
//        TreeNode p = node;
//        LinkedList<TreeNode> stack = new LinkedList<>();
//        int sum = 0;
//        while (p != null || !stack.isEmpty()) {
//            if (p != null) {
//                stack.push(p);
//                p = p.left;
//            } else {
//                TreeNode pop = stack.pop();
//                if (pop.val > high) {
//                    break;
//                }
//                // && pop.val <= high
//                if (pop.val >= low) {
//                    sum += pop.val;
//                }
//                p = pop.right;
//            }
//        }
//        return sum;
//    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(5, new TreeNode(3), new TreeNode(7));
        TreeNode n2 = new TreeNode(15, null, new TreeNode(18));
        TreeNode root = new TreeNode(10, n1, n2);
        int sum = new LeetCode938().rangeSumBST(root, 7, 15);
        System.out.println(sum);
    }
}
