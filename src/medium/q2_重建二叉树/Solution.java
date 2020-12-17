package medium.q2_重建二叉树;


/**
 * 思路:
 * 1. 前序遍历的第一个是root，然后找到这个去中序找旁边2个为左子树和右子树。不断的构建
 * <p>
 * 题解：
 * 看看4种解法：https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/solution/4chong-jie-fa-di-gui-zhan-dui-lie-by-sdwwld/
 */
class Solution {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}