package simple.q7_二进制中1的个数;

/**
 * 我的思路：
 * 判断有多少个1，那么就是要一个个比较，可以使用位运算右移，然后和1做与运算如果是1返回1，如果是0返回0。这样就是O(n)的计算
 * 是否有O(1)解法？？？
 * <p>
 * 题解：
 * 1. 并没有找到O(1)的解法，但是确实有更好的解法
 * 2. 我的算法并不是O(n)的，而是O(log2n)的，因为while循环判断的条件是n为0的时候，这会取决于n值最高位1在第几个。
 * 3. 目前找到的最好解法：使用 n &= n - 1
 * 3.1 n-1 会将n最后一个1位置 变成0 后面则全变成1，这样&运算后，最后一位1会被置为0，这样就不断的去除后面的1 知道数字为0的时候。算了几次就是几个1
 * 算法复杂度为O(M) M为n中1的个数，速度优越，因为少了那些最高位为1后面位数中有0的个数。
 *
 * BTW: java二进制可以直接输入啊
 *
 * @author zhaohaoren
 */
public class Solution {

    public static int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            if ((n & 1) == 1) {
                count++;
            }
            n = n >>> 1;
        }
        return count;
    }

    public static int hammingWeightBetter(int n) {
        int count = 0;
        while (n != 0) {
            //只是代码做了一些简化
            count += n & 1;
            n >>>= 1;
        }
        return count;
    }

    /**
     * 算法 n中有几个1就走几步
     */
    public static int hammingWeightBest(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            // 1 0 1 0 1 000
            // 1 0 1 0 0 111
            //=1 0 1 0 0 000
            n &= n - 1;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(hammingWeight(00000000000000000000000000001011));
    }
}