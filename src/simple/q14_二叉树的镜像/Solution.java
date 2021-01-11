package simple.q14_二叉树的镜像;


/**
 * 我的思路：
 * 交换一下root下的左子树和右子树即可
 * 然后递归左子树 和右子树不断的进行交换
 * 主要注意点就是：左子树为空，或者右子树为空就不要在递归了，只递归有分支的那一个端。
 * <p>
 * 题解：
 * 还可以辅助栈来解决，既然是递归的，都可以使用栈来帮忙的。就不多写了
 *
 * @author zhaohaoren
 */
class Solution {
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null || root.right == null && root.left == null) {
            return root;
        }
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        if (root.right != null) {
            mirrorTree(root.right);
        }
        if (root.left != null) {
            mirrorTree(root.left);
        }
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