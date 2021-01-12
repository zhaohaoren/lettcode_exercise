package simple.q15_对称的二叉树;


import anno.Method;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的思路：
 * 这个使用层次遍历然后计算这一层上所有节点是否对称就可以了
 * <p>
 * 题解：
 * 我以为层次遍历的方式去判断会比递归好些，但其实不是这样。
 * 递归的思想也很简单，递归函数传入2个节点：一个最左边的，一个最右边的
 *
 * @author zhaohaoren
 */
class Solution {

    @Method(1)
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        List<TreeNode> levelNodes = new ArrayList<>();
        levelNodes.add(root.left);
        levelNodes.add(root.right);
        while (true) {
            int index = 0;
            // 比较一层是否相同
            while (true) {
                TreeNode pre = levelNodes.get(index);
                TreeNode last = levelNodes.get(levelNodes.size() - 1 - index);
                if (pre != null && last != null) {
                    if (pre.val != last.val) {
                        return false;
                    }
                } else {
                    if (pre != last) {
                        return false;
                    }
                }
                index++;
                if (index > (levelNodes.size() / 2)) {
                    break;
                }
            }
            //添加下一层
            boolean allNull = true;
            List<TreeNode> newLevelNodes = new ArrayList<>(2 * levelNodes.size());
            for (TreeNode n : levelNodes) {
                if (n != null) {
                    if (n.left != null || n.right != null) {
                        allNull = false;
                    }
                    newLevelNodes.add(n.left);
                    newLevelNodes.add(n.right);
                }

            }
            if (allNull) {
                return true;
            }
            levelNodes = newLevelNodes;
        }
    }

    public boolean isSymmetricBetter(TreeNode root) {
        return root == null || recur(root.left, root.right);
    }

    /**
     * 以后需要这种函数的尽管大胆的抽出来
     */
    boolean recur(TreeNode L, TreeNode R) {
        if (L == null && R == null) {
            return true;
        }
        if (L == null || R == null || L.val != R.val) {
            return false;
        }
        return recur(L.left, R.right) && recur(L.right, R.left);
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(2);
//        root.right = new TreeNode(2);
//        root.left.left = new TreeNode(3);
//        root.left.right = new TreeNode(4);
//        root.right.left = new TreeNode(4);
//        root.right.right = new TreeNode(3);
        System.out.println(new Solution().isSymmetric(root));
    }
}