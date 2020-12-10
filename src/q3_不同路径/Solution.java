package q3_不同路径;


/**
 * 思路:
 * mxn 的一个数 （m-1）次+1 (n-1）次+m 有多少总排列组合。
 * 思路大体靠上去了，但是没做出来！
 * <p>
 * 题解：
 * 1. 动态规划（todo:专门学习）
 * 2. 每个格子标号，则1 ~ m*n。从1到m*n需要步数一定是m+n-2步数，而步的行为只有向上，向下2种：
 * => 所以，到m*n格子，必定要向右走m-1步，以及向下走n-1步。
 * 那么就是我们在m+n-2步数中，选择在哪一步我选择向右走，所以所有的可能的排列组合就是：C (m+n-2) (m-1)
 * <p>
 * 重点：就是问题的转化！走路的问题转化为数学的排列组合问题。
 */
class Solution {

    public static int uniquePaths(int m, int n) {
        long ans = 1;
        for (int x = n, y = 1; y < m; ++x, ++y) {
            ans = ans * x / y;
        }
        return (int) ans;
    }

    public static void main(String[] args) {
        System.out.println(uniquePaths(3, 2));
    }
}