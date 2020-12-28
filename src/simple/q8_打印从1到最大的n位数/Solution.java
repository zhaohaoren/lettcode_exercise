package simple.q8_打印从1到最大的n位数;

import java.util.Arrays;

/**
 * 我的思路：
 * 很直接，没啥好说的
 * <p>
 * 题解：
 * 这题好像出的有问题，剑指offer有大数问题限制
 *
 * @author zhaohaoren
 */
public class Solution {

    public static int[] printNumbers(int n) {
        int[] res = new int[(int) (Math.pow(10, n) - 1)];
        for (int i = 1; i <= res.length; i++) {
            res[i - 1] = i;
        }
        return res;
    }

    public static void main(String[] args) {
        Arrays.stream(printNumbers(2)).forEach(System.out::println);
    }
}