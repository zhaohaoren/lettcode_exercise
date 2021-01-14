package simple.q16_顺序打印矩阵;


import anno.Method;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的思路：
 * 数组下标记为i,j，那么移动的顺序：
 * （i++，j） （i, j++) (i--，j) (i, j--)  4个方向一个循环
 * 将遍历的数组都置为空
 * <p>
 * 题解：
 *
 * @author zhaohaoren
 */
class Solution {

    public int[] spiralOrder(int[][] matrix) {
        int[] res = new int[matrix.length * matrix[0].length];
        int i = 0;
        int j = 0;
        int index = 0;
        while (true) {
            res[index] = matrix[i][j];
            index++;
            matrix[i][j] = '\0';
            if (i < matrix[0].length && matrix[i + 1][j] != '\0') {
                i++;
            } else if (j < matrix.length - 1) {

            }
        }
    }
}