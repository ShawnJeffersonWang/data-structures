package com.shawn.algorithm;


public class LeetCode1008_2 {

    // 分治法
    // 8, 5, 1, 7, 10, 12
    // 8 根 5, 1, 7 左 10, 12 右
    // 5 根 1 左 7 右
    // 10 根 12 右

    /*

             8
           /   \
          5    10
         / \     \
        1   7    12
     */
    public TreeNode bstFromPreorder(int[] preorder) {
        return partition(preorder, 0, preorder.length - 1);
    }

    private TreeNode partition(int[] preorder, int start, int end) {
        if (start > end) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[start]);
        int index = start + 1;
        while (index <= end) {
            if (preorder[index] > preorder[start]) {
                break;
            }
            index++;
        }
        // 退出循环后 index就是右子树的起点
        root.left = partition(preorder, start + 1, index - 1);
        root.right = partition(preorder, index, end);
        return root;
    }
}
