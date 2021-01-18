package medium.q11_树的子结构;

/**
 * 我的思路：
 * 首先需要遍历下树A，找到和树B的root相同的节点。
 * 然后开始递归，要以B为基准遍历B树，如果按照B树的方向，能匹配那么就是true，否则找下一个和B root一样的
 * 题解：
 * 1. 第一步，遍历A 找到和B相同的，然后DFS
 * 2. 第二步，遍历B， 和A做对照。
 *
 * @author zhaohaoren
 */
class Solution {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        if (A.val == B.val) {
            if (isSame(A, B)) {
                return true;
            }
        }
        return isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }


    /**
     * 遍历树B，每次遍历都去找下A，如果全部都相同就返回true
     */
    public boolean isSame(TreeNode A, TreeNode B) {
        if (B == null) {
            return true;
        }
        if (A == null || B.val != A.val) {
            return false;
        }
        return isSame(A.left, B.left) && isSame(A.right, B.right);
    }

    /**
     * 更好的写法
     */
    public boolean isSubStructureBetter(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        return dfs(A, B) || isSubStructureBetter(A.left, B) || isSubStructureBetter(A.right, B);
    }
    public boolean dfs(TreeNode A, TreeNode B) {
        if (B == null) {
            return true;
        }
        if (A == null) {
            return false;
        }
        return A.val == B.val && dfs(A.left, B.left) && dfs(A.right, B.right);
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
        root.right = new TreeNode(2);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(4);

        System.out.println(new Solution().isSubStructure(root, new TreeNode(3)));
    }
}