package medium.q2_重建二叉树;


import java.util.Arrays;

/**
 * 思路:
 * 1. 前序遍历的第一个是root，然后找到这个去中序找旁边2个为左子树和右子树。不断的构建
 * <p>
 * 题解：
 * 看看4种解法：https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/solution/4chong-jie-fa-di-gui-zhan-dui-lie-by-sdwwld/
 */
class Solution {

    /**
     * 注意递归的思想，在于找出当前节点的左节点和右节点
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }
        // 当前的pre的第一个一定是root
        TreeNode root = new TreeNode(preorder[0]);
        int rootIndex = 0;
        // 找出当前root在inorder中的位置
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == preorder[0]) {
                rootIndex = i;
                break;
            }
        }
        // 边界是 pre: 当前root的下一个元素开始, 后面rootIndex位置的数都是左子树的前序遍历（重点！！！画图更清晰一点，这也算是树的特性了吧）
        root.left = buildTree(Arrays.copyOfRange(preorder, 1, rootIndex + 1), Arrays.copyOfRange(inorder, 0, rootIndex));
        root.right = buildTree(Arrays.copyOfRange(preorder, rootIndex + 1, preorder.length), Arrays.copyOfRange(inorder, rootIndex + 1, inorder.length));
        return root;
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