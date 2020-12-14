package medium.q1_二维数组中的查找;


/**
 * 思路:
 * 1. 暴力破解（显然不会这么写）
 * 2. 左上角开始，遇到大的往下走？ 不行！当遇到比当前大，不知道往下还是往后走！
 * <p>
 * 题解：
 * 重点：从右上角去看！！！！从右上角去看！！！！从右上角去看！！！！
 * 站在右上角看。这个矩阵其实就像是一个Binary Search Tree。然后二分查找就行了！
 * 还有一点：注意边界。最好画个图出来，标记x,y，不然容易记混了。
 */
class Solution {

    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        // 边界问题，一定先考虑
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int h = matrix.length;
        int w = matrix[0].length;

        int curX = w - 1;
        int curY = 0;

        while (curX >= 0 && curY < h) {
            int value = matrix[curY][curX];
            if (value == target) {
                return true;
            }
            if (value < target) {
                curY++;
            } else {
                curX--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(findNumberIn2DArray(new int[][]{{1, 1}}, 0));
    }
}