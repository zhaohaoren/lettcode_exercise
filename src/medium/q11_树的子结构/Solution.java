package medium.q11_树的子结构;

/**
 * 我的思路：
 * 首先需要遍历下树A，找到和树B的root相同的节点。
 * 然后开始递归，要以B为基准遍历B树，如果按照B树的方向，能匹配那么就是true，否则找下一个和B root一样的
 * 题解：
 *
 * @author zhaohaoren
 */
class Solution {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        int bRoot = B.val;


    }


    public boolean isSame(TreeNode A, TreeNode B) {

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