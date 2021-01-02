package medium.q9_剪绳子2;

/**
 * 和q7_剪绳子一样，只是注意大数运算，需要每一步取余
 *
 * @author zhaohaoren
 */
class Solution {

    public int cuttingRope(int n) {
        if (n == 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        long res = 1;
        while (n > 4) {
            res *= 3;
            res = res % 1000000007;
            n -= 3;
        }
        return (int) (res * n % 1000000007);
    }
}