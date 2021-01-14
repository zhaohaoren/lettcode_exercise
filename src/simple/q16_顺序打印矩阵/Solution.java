package simple.q16_顺序打印矩阵;


/**
 * 我的思路：
 * 数组下标记为i,j，那么移动的顺序：
 * （i++，j） （i, j++) (i--，j) (i, j--)  4个方向一个循环
 * 将遍历的数组都置为空
 * 1 2 3
 * 4 5 6
 * 7 8 9
 * <p>
 * 题解：
 * 原来不止我一个质疑这是个简单题 o(╥﹏╥)o
 * 思路好简单，但是边界处理起来好复杂
 *
 * @author zhaohaoren
 */
class Solution {

    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0) {
            return new int[]{};
        }

        int[] res = new int[matrix.length * matrix[0].length];
        int index = 1;

        int i = 0;
        int j = 0;

        res[0] = matrix[0][0];
        // 测试集合里面有0的，这么判断就有问题，应该计算宽度，每次遍历一圈就应该减1
        matrix[0][0] = '\0';


        int high_1 = 0;
        int high_2 = matrix.length - 1;
        int width_1 = 0;
        int width_2 = matrix[0].length - 1;

        while (true) {
            if (index > res.length - 1) {
                break;
            }
            while (width_1 <= j + 1 && j + 1 <= width_2) {
                j++;
                res[index] = matrix[i][j];
                index++;
            }
            high_1++;
            if (index > res.length - 1) {
                break;
            }
            while (i + 1 >= high_1 && i + 1 <= high_2) {
                i++;
                res[index] = matrix[i][j];
                index++;
            }
            width_2--;
            if (index > res.length - 1) {
                break;
            }
            while (width_1 <= j - 1 && j - 1 <= width_2) {
                j--;
                res[index] = matrix[i][j];
                index++;
            }
            high_2--;
            if (index > res.length - 1) {
                break;
            }
            while (i - 1 <= high_2 && i - 1 >= high_1) {
                i--;
                res[index] = matrix[i][j];
                index++;
            }
            width_1++;
            if (index > res.length - 1) {
                break;
            }
        }
        return res;
    }


    /*
    1 2 3 4
    5 6 7 8
    9 10 11 12
     */

    public static void main(String[] args) {
//        int[][] maat = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
//        int[][] maat = new int[][]{{2, 5}, {8, 4}, {0, -1}};
        int[][] maat = new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}};
//        int[][] maat = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        System.out.println(new Solution().spiralOrder(maat));
    }
}