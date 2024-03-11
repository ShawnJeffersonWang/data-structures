package com.shawn.algorithm;

/**
 * 根据前序遍历构造二叉搜索树
 */
public class LeetCode1008 {

    public TreeNode bstFromPreorder(int[] preorder) {
        TreeNode root = new TreeNode(preorder[0]);
        // n * log(n)
        for (int i = 1; i < preorder.length; i++) {
            int val = preorder[i];
            // log(n)
            insert(root, val);
        }
        return root;
    }

    private TreeNode insert(TreeNode node, int val) {
        if (node == null) {
            return new TreeNode(val);
        }
        if (val < node.val) {
            node.left = insert(node.left, val);
        } else if (val > node.val) {
            node.right = insert(node.right, val);
        }
        return node;
    }

    public static void main(String[] args) {
        TreeNode t1 = new LeetCode1008().bstFromPreorder(new int[]{8, 5, 1, 7, 10, 12});
        TreeNode t2 = new TreeNode(8, new TreeNode(5, new TreeNode(1), new TreeNode(7)), new TreeNode(10, null, new TreeNode(12)));
        System.out.println(isSameTree(t1, t2));
    }

    private static boolean isSameTree(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        if (t1.val != t2.val) {
            return false;
        }
        return isSameTree(t1.left, t2.left) && isSameTree(t1.right, t2.right);
    }
}
