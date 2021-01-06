package medium.q7_数值的整数次方;

/**
 * 思路:
 * 直接计算？卡在了-2147483648这个上面，这个指数
 * https://zhuanlan.zhihu.com/p/217650378
 * 大概问题就是
 * -1.00000 , -2147483648
 * 2.00000 -2147483648
 * 这些在边界的情况
 * 最终没做出来
 * <p>
 * 题解：
 * https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/solution/mian-shi-ti-16-shu-zhi-de-zheng-shu-ci-fang-kuai-s/
 * 数学推演方法解决
 */
class Solution {
    public double myPow(double x, int n) {
        if (x == 0) {
            return 0;
        }
        long b = n;
        double res = 1.0;
        if (b < 0) {
            x = 1 / x;
            b = -b;
        }
        while (b > 0) {
            if ((b & 1) == 1) {
                res *= x;
            }
            x *= x;
            b >>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        int x = -1;
        int y = -x;
        System.out.println(y);

        System.out.println(new Solution().myPow(2D, -2147483648));
    }
}