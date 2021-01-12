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
 * <p>
 * <p>
 * 做递归思考三步：
 * <p>
 * 递归的函数要干什么？
 * 函数的作用是判断传入的两个树是否镜像。
 * 输入：TreeNode left, TreeNode right
 * 输出：是：true，不是：false
 * 递归停止的条件是什么？
 * 左节点和右节点都为空 -> 倒底了都长得一样 ->true
 * 左节点为空的时候右节点不为空，或反之 -> 长得不一样-> false
 * 左右节点值不相等 -> 长得不一样 -> false
 * 从某层到下一层的关系是什么？
 * 要想两棵树镜像，那么一棵树左边的左边要和二棵树右边的右边镜像，一棵树左边的右边要和二棵树右边的左边镜像
 * 调用递归函数传入左左和右右
 * 调用递归函数传入左右和右左
 * 只有左左和右右镜像且左右和右左镜像的时候，我们才能说这两棵树是镜像的
 * 调用递归函数，我们想知道它的左右孩子是否镜像，传入的值是root的左孩子和右孩子。这之前记得判个root==null。
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